/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DbConnection;
import Models.Dieta;
import Models.Refeicao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */
public class DietaDAO {
    
    RefeicaoDAO refeicaoDAO = new RefeicaoDAO();

    public boolean adiciona(Dieta dieta) {
        int i = 2;
        String sql = "INSERT INTO dieta"
                + "(nome, refeicao1, refeicao2, refeicao3, refeicao4, gordura, proteina, carboidrato, createDate)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new DbConnection().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, dieta.getNome());
            for (int refeicao : dieta.getRefeicoesId()) {
                stmt.setInt(i++, refeicao);
            }
            stmt.setFloat(6, dieta.getGordura());
            stmt.setFloat(7, dieta.getProteina());
            stmt.setFloat(8, dieta.getCarboidrato());
            LocalDateTime data = LocalDateTime.now();
            stmt.setString(9, data.toString());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(Dieta dieta) {
        int i = 1;
        String sql = "UPDATE dieta "
                + "SET refeicao1 = ?, refeicao2 = ?, refeicao3 = ?, refeicao4 = ?, updateDate = ?"
                + "WHERE nome = ?";

        try (Connection connection = new DbConnection().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (int refeicao : dieta.getRefeicoesId()) {
                stmt.setInt(i++, refeicao);
            }
            LocalDateTime updateDate = LocalDateTime.now();
            stmt.setString(5, updateDate.toString());
            stmt.setString(6, dieta.getNome());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }  
        

    public Dieta buscaPorNome(String nome) {
        String sql = "SELECT * FROM dieta WHERE nome = ? AND visible = 1";

        try (Connection connection = new DbConnection().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            Dieta dieta = new Dieta();
            List<Integer> refeicoes = new ArrayList<>();

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    dieta.setId(rs.getInt("id_"));
                    dieta.setNome(rs.getString("nome"));
                    for (int i = 1; i <= 4; i++) {
                        refeicoes.add(rs.getInt("refeicao" + i));
                    }
                    dieta.setGordura(rs.getFloat("gordura"));
                    dieta.setProteina(rs.getFloat("proteina"));
                    dieta.setCarboidrato(rs.getFloat("carboidrato"));
                    dieta.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));
                    dieta.setRefeicoesId(refeicoes);
                }

                return dieta;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void remove(String nome){
        String sql = "UPDATE dieta SET visible = 0 WHERE nome = ?";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Refeicao> retornaRefeicoes(Dieta dieta){
        List<Refeicao> refeicoes = new ArrayList<>();
        
        for (Integer idRefeicao : dieta.getRefeicoesId()){
            refeicoes.add(refeicaoDAO.buscaPorId(idRefeicao));
        }
        
        return refeicoes;
    }
}

