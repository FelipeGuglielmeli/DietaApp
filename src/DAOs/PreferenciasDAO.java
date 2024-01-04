/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DbConnection;
import Models.Preferencias;
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
public class PreferenciasDAO {
    
    public boolean Adiciona (Preferencias preferencias){
        int i = 2;
        String sql = "INSERT INTO preferencias"
                    +"(nome, alimento1, alimento2, alimento3, alimento4, alimento5, alimento6, alimento7, alimento8, createDate)"
                    +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = new DbConnection().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, preferencias.getNome());
            for (String alimento : preferencias.getPreferencias())
                stmt.setString(i++, alimento);
            stmt.setString(10, LocalDateTime.now().toString());
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean altera (Preferencias preferencias){
        int i = 1;
        String sql = "UPDATE preferencias"
                    +"SET alimento1 = ?, alimento2 = ?, alimento3 = ?, alimento4 = ?, alimento5 = ?, alimento6 = ?, alimento7 = ?, alimento8 = ?, updateDate = ?"
                    +"WHERE nome = ?";
        
        try (Connection connection = new DbConnection().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            for (String alimento : preferencias.getPreferencias())
                stmt.setString(i, alimento);
            stmt.setString(9, LocalDateTime.now().toString());
            stmt.setString(10, preferencias.getNome());
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Preferencias buscaPorNome(String nome){
        String sql = "SELECT * FROM preferencias WHERE nome = ? AND visible =1";
        
        try (Connection connection = new DbConnection().getConnection(); 
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            Preferencias preferencias = new Preferencias();
            List<String> alimentos = new ArrayList<>();
            
             try (ResultSet rs = stmt.executeQuery()) {
                 while (rs.next()) {
                     preferencias.setId(rs.getInt("id_"));
                     preferencias.setNome(rs.getString("nome"));
                     for (int i = 1; i <= 8; i++)
                         alimentos.add(rs.getString("alimento"+i));
                     preferencias.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));
                     preferencias.setPreferencias(alimentos);
                 }
                 
                 return preferencias;
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean remove (String nome){
        String sql = "UPDATE preferencias SET visible = 0 WHERE nome = ?";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            stmt.execute();  
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
