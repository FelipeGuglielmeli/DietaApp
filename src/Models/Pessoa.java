/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDateTime;

/**
 *
 * @author felip
 */
public class Pessoa {
    private long id;
    private String nome;
    private String sexo;
    private LocalDateTime data_nascimento;
    private String login;
    private String senha;
    private String tipoUsuario;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    
    public Pessoa() {
        this.tipoUsuario = "paciente";
        createDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDateTime getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDateTime data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", data_nascimento=" + data_nascimento + ", login=" + login + ", senha=" + senha + ", tipoUsuario=" + tipoUsuario + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
    }  
}
