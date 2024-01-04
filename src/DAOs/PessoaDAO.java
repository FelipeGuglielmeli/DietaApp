/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DbConnection;
import Models.Pessoa;
import static java.lang.Integer.parseInt;
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
public class PessoaDAO {
    
    public boolean adiciona(Pessoa pessoa){
        String sql = "insert into pessoa"
                    +"(nome,sexo,dataNasc,login,senha,tipoUsuario,createDate) "
                    +"values (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setString(3, pessoa.getData_nascimento().toString());
            stmt.setString(4, pessoa.getLogin());
            stmt.setString(5, pessoa.getSenha());
            stmt.setString(6, pessoa.getTipoUsuario());
            LocalDateTime data = LocalDateTime.now();
            stmt.setString(7, data.toString());

            stmt.execute(); 
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean Altera (Pessoa pessoa){
        String sql = "UPDATE pessoa " 
                     +"SET nome = ?, sexo = ?,dataNasc = ?, login = ?, senha = ?, tipoUsuario = ?, updateDate = ? " 
                     +"WHERE nome = ?";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getSexo());
            stmt.setString(3, pessoa.getData_nascimento().toString());
            stmt.setString(4, pessoa.getLogin());
            stmt.setString(5, pessoa.getSenha());
            stmt.setString(6, pessoa.getTipoUsuario());
            LocalDateTime data = LocalDateTime.now();
            stmt.setString(7, data.toString());
            stmt.setString(8, pessoa.getNome());
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }   
    }
    
    public Pessoa buscaLogin(String login, String senha){
        String sql = "SELECT * FROM pessoa WHERE login = ? AND senha = ? AND visible = 1";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, login);
            stmt.setString(2, senha);
            Pessoa pessoa = new Pessoa();
            
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    pessoa.setId(parseInt(rs.getString("id_")));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setSexo(rs.getString("sexo"));
                    pessoa.setData_nascimento(LocalDateTime.parse(rs.getString("dataNasc")));
                    pessoa.setLogin(rs.getString("login"));
                    pessoa.setSenha(rs.getString("senha"));
                    pessoa.setTipoUsuario(rs.getString("tipoUsuario"));        
                    pessoa.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));
                }

                return pessoa;
            }    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Pessoa buscaPorNome(String nome){
        String sql = "SELECT * FROM pessoa WHERE nome = ? AND visible = 1";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setString(1, nome);
            Pessoa pessoa = new Pessoa();
            
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    pessoa.setId(parseInt(rs.getString("id_")));
                    pessoa.setNome(rs.getString("nome"));
                    pessoa.setSexo(rs.getString("sexo"));
                    pessoa.setData_nascimento(LocalDateTime.parse(rs.getString("dataNasc")));
                    pessoa.setLogin(rs.getString("login"));
                    pessoa.setSenha(rs.getString("senha"));
                    pessoa.setTipoUsuario(rs.getString("tipoUsuario"));        
                    pessoa.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));
                }

                return pessoa;
            }    
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean remove(String nome) {
        String sql = "UPDATE pessoa SET visible = 0 WHERE nome = ?";

        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Pessoa> mostraTodos() {
        String sql = "SELECT * FROM pessoa WHERE visible = 1";
        List retorno = new ArrayList<Pessoa>();
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();

                pessoa.setId(parseInt(rs.getString("id_")));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setSexo(rs.getString("sexo"));
                pessoa.setData_nascimento(LocalDateTime.parse(rs.getString("dataNasc")));
                pessoa.setLogin(rs.getString("login"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setTipoUsuario(rs.getString("tipoUsuario"));                
                pessoa.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));

                retorno.add(pessoa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return retorno;
    }
    
    public boolean alteraTipoUsuario(String nome, String tipoUsuario){
        String sql = "UPDATE pessoa SET tipoUsuario = ? WHERE nome = ?";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, tipoUsuario);
            stmt.setString(2, nome);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
