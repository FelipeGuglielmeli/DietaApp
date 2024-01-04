/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DbConnection;
import Models.AvaliacaoFisica;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */
public class AvaliacaoFisicaDAO {

    public boolean adiciona(AvaliacaoFisica avaliacao) {
        String sql = "insert into avaliacaofisica"
                + " (nome,peso,altura,idade,pescoco,cintura,quadril,fatorAtividade,createDate) "
                + " values (?,?,?,?,?,?,?,?,?)";

        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, avaliacao.getNome());
            stmt.setFloat(2, avaliacao.getPeso());
            stmt.setFloat(3, avaliacao.getAltura());
            stmt.setFloat(4, avaliacao.getIdade());
            stmt.setFloat(5, avaliacao.getPescoco());
            stmt.setFloat(6, avaliacao.getCintura());
            stmt.setFloat(7, avaliacao.getQuadril());
            stmt.setFloat(8, avaliacao.getFatorAtividade());
            LocalDateTime data = LocalDateTime.now();
            stmt.setString(9, data.toString());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean Altera (AvaliacaoFisica avaliacao){
        String sql = "UPDATE avaliacaofisica " 
                     +"SET peso = ?, idade = ?, pescoco = ?, cintura = ?, quadril = ?, fatorAtividade = ?, updateDate = ? " 
                     +"WHERE nome = ?";
        
        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setFloat(1, avaliacao.getPeso());
            stmt.setFloat(2, avaliacao.getIdade());
            stmt.setFloat(3, avaliacao.getPescoco());
            stmt.setFloat(4, avaliacao.getCintura());
            stmt.setFloat(5, avaliacao.getQuadril());
            stmt.setFloat(6, avaliacao.getFatorAtividade());
            LocalDateTime updateDate = LocalDateTime.now();
            stmt.setString(7, updateDate.toString());
            stmt.setString(8, (avaliacao.getNome()));
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }   
    }
    

    public AvaliacaoFisica buscaPorNome(String nome) {
        String sql = "SELECT * FROM avaliacaofisica WHERE nome = ? AND visible = 1";

        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            AvaliacaoFisica avaliacao = new AvaliacaoFisica();

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    avaliacao.setId(parseInt(rs.getString("id_")));
                    avaliacao.setNome(rs.getString("nome"));
                    avaliacao.setPeso(rs.getFloat("peso"));
                    avaliacao.setAltura(rs.getFloat("altura"));
                    avaliacao.setIdade(rs.getFloat("idade"));
                    avaliacao.setPescoco(rs.getFloat("pescoco"));
                    avaliacao.setCintura(rs.getFloat("cintura"));
                    avaliacao.setQuadril(rs.getFloat("quadril"));
                    avaliacao.setFatorAtividade(rs.getFloat("fatorAtividade"));
                    avaliacao.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));
                }

                return avaliacao;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(String nome) {
        String sql = "UPDATE avaliacaofisica SET visible = 0 WHERE nome = ?";

        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<AvaliacaoFisica> mostraTodos() {
        String sql = "SELECT * FROM avaliacaofisica";
        List retorno = new ArrayList<AvaliacaoFisica>();

        try (Connection connection = new DbConnection().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AvaliacaoFisica aval = new AvaliacaoFisica();

                aval.setId(parseInt(rs.getString("id_")));
                aval.setNome(rs.getString("nome"));
                aval.setPeso(rs.getFloat("peso"));
                aval.setAltura(rs.getFloat("altura"));
                aval.setIdade(rs.getFloat("idade"));
                aval.setPescoco(rs.getFloat("pescoco"));
                aval.setCintura(rs.getFloat("cintura"));
                aval.setQuadril(rs.getFloat("quadril"));
                aval.setFatorAtividade(rs.getFloat("fatorAtividade"));
                aval.setCreateDate(LocalDateTime.parse(rs.getString("createDate")));

                retorno.add(aval);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return retorno;
    }
}
