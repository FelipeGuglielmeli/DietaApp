/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DbConnection;
import Models.Alimento;
import Models.Refeicao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author felip
 */
public class RefeicaoDAO {
    
    AlimentoDAO alimentoDAO = new AlimentoDAO();
    
    public int adiciona(Refeicao refeicao){
        int i = 2;
        String sql = "INSERT INTO refeicao"
                    +"(nome, alimento1, porcao1, alimento2, porcao2, alimento3, porcao3, alimento4, porcao4, proteina, gordura, carboidrato, createDate)"
                    +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)){
            
            stmt.setString(1, refeicao.getNome());
            for(Map.Entry<String, Integer> entry : refeicao.getAlimentos().entrySet()){
                stmt.setString(i++, entry.getKey());               
                stmt.setInt(i++, entry.getValue());              
            }
            stmt.setFloat(10, refeicao.getProteina());
            stmt.setFloat(11, refeicao.getGordura());
            stmt.setFloat(12, refeicao.getCarboidrato());
            LocalDateTime data = LocalDateTime.now();
            stmt.setString(13, data.toString());
           
            stmt.execute();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if(generatedKeys.next()){
                    int id = generatedKeys.getInt(1);
                    return id;
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    
    public void altera(Refeicao refeicao){
        int i = 1;
        String sql = "UPDATE refeicao "
                    +"SET alimento1 = ?, porcao1 = ?, alimento2 = ?, porcao2 = ?, alimento3 = ?, porcao3 = ?, alimento4 = ?, porcao4 = ?, updateDate = ?"
                    +"WHERE id_ = ?";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            
            for(Map.Entry<String, Integer> entry : refeicao.getAlimentos().entrySet()){
                stmt.setString(i++, entry.getKey());               
                stmt.setInt(i++, entry.getValue());              
            }
            LocalDateTime updateDate = LocalDateTime.now();
            stmt.setString(9, updateDate.toString());
            stmt.setInt(10, refeicao.getId());
            
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Refeicao buscaPorId(int id){
        String sql = "SELECT * FROM refeicao WHERE id_ = ?";
                
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            Refeicao refeicao = new Refeicao();
            Map<String, Integer> alimentos = new HashMap<>();
            
            try (ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    refeicao.setId(rs.getInt("id_"));
                    refeicao.setNome(rs.getString("nome"));
                    for(int i = 1; i <= 4; i++)
                        alimentos.put(rs.getString("alimento"+i), rs.getInt("porcao"+i));
                    refeicao.setProteina(rs.getFloat("proteina"));
                    refeicao.setGordura(rs.getFloat("gordura"));
                    refeicao.setCarboidrato(rs.getFloat("carboidrato"));
                    refeicao.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));
                    refeicao.setAlimentos(alimentos);
                }
                
                return refeicao;
            }                    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void remove(int id){
        String sql = "UPDATE refeicao SET visible = 0 WHERE id_ = ?";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Alimento> retornaAlimentos(Refeicao refeicao){
        List<Alimento> alimentos = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : refeicao.getAlimentos().entrySet()){
            alimentos.add(alimentoDAO.buscaPorNome(entry.getKey()));
        }
        
        return alimentos;
    }
}
