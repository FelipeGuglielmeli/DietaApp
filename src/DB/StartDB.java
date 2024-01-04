/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import Models.Alimento;
import static java.lang.Float.parseFloat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author felip
 */
public class StartDB {
    public static void main(String[] args) {
        try (Connection connection = new DbConnection().getConnection()) {//funcao de inserir dados
            System.out.println("Conexão aberta!");
            
            String sql = "insert into alimentos"
                    + " (nome,carboidratos,proteinas,gorduras,createDate) "
                    + " values (?,?,?,?,?)";
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "Arroz integral");
            stmt.setString(2, "25.8");
            stmt.setString(3, "2.6");
            stmt.setString(4, "1.0");
            LocalDateTime dat = LocalDateTime.now();
            stmt.setString(5, dat.toString());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        try (Connection connection = new DbConnection().getConnection()) {
            String sql = "SELECT * FROM alimentos WHERE id_ = 2";
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            ResultSet rs = stmt.executeQuery();
            Alimento a = new Alimento();
            while(rs.next()){               
                a.setNome(rs.getString("nome"));
                a.setCarboidratos(parseFloat(rs.getString("carboidratos")));
                a.setProteinas(parseFloat(rs.getString("proteinas")));
                a.setGordura(parseFloat(rs.getString("gorduras")));
                
                
                LocalDateTime data = LocalDateTime.parse(rs.getString("createDate"));
                
                a.setCreateDate(data);
            }
            
            rs.close();
            stmt.close();
            a.setCalorias();
            System.out.println(a.toString());
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
