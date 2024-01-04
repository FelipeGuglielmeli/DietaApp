/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DbConnection;
import Models.Alimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */
public class AlimentoDAO {
    
    public boolean adiciona(Alimento alimento){
        String sql = "insert into alimentos"
                +"(nome, carboidratos, proteinas, gorduras, createDate)"
                +"values (?,?,?,?,?)";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            
            stmt.setString(1, alimento.getNome());
            stmt.setFloat(2, alimento.getCarboidratos());
            stmt.setFloat(3, alimento.getProteinas());
            stmt.setFloat(4, alimento.getGordura());            
            LocalDateTime data = LocalDateTime.now();
            stmt.setString(5, data.toString());
            
            stmt.execute();    
            return true;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean Altera (Alimento alimento){
        String sql = "UPDATE alimentos "
                    +"SET carboidratos = ?, proteinas = ?, gorduras = ?, updateDate = ?"
                    +"WHERE nome = ?";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            
            stmt.setFloat(1, alimento.getCarboidratos());
            stmt.setFloat(2, alimento.getProteinas());
            stmt.setFloat(3, alimento.getGordura());
            LocalDateTime updateDate = LocalDateTime.now();
            stmt.setString(4, updateDate.toString());
            stmt.setString(5, alimento.getNome());
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Alimento buscaPorNome(String nome){
        String sql = "SELECT * FROM alimentos WHERE nome = ? AND visible = 1";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            
            stmt.setString(1, nome);
            Alimento alimento = new Alimento();
            
            try (ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    alimento.setId(rs.getInt("id_"));
                    alimento.setNome(rs.getString("nome"));
                    alimento.setCarboidratos(rs.getFloat("carboidratos"));
                    alimento.setProteinas(rs.getFloat("proteinas"));
                    alimento.setGordura(rs.getFloat("gorduras"));
                    alimento.setCalorias();
                    alimento.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));                  
                }
                
                return alimento;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean remove(String nome){
        String sql = "UPDATE alimentos SET visible = 0 WHERE nome = ?";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Alimento> mostraTodos(){
        String sql = "SELECT * FROM alimentos WHERE visible = 1";
        List retorno = new ArrayList<Alimento>();
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
            
            while (rs.next()) {
                Alimento alimento = new Alimento();

                alimento.setId(rs.getInt("id_"));
                alimento.setNome(rs.getString("nome"));
                alimento.setCarboidratos(rs.getFloat("carboidratos"));
                alimento.setProteinas(rs.getFloat("proteinas"));
                alimento.setGordura(rs.getFloat("gorduras"));
                alimento.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));

                retorno.add(alimento);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return retorno;
    }
}
