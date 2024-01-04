/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.time.LocalDateTime;

/**
 *
 * @author felip
 */
public class Alimento {
    
    private long id;
    private String nome;
    private float carboidratos;
    private float proteinas;
    private float gordura;
    private float calorias;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Alimento() {     
        createDate.now();
    }
    
    public void setId(long id){
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(float carboidratos) {
        this.carboidratos = carboidratos;
    }

    public float getProteinas() {
        return proteinas;
    }

    public void setProteinas(float proteinas) {
        this.proteinas = proteinas;
    }

    public float getGordura() {
        return gordura;
    }

    public void setGordura(float gordura) {
        this.gordura = gordura;
    }

    public float getCalorias() {
        return calorias;
    }

    public void setCalorias() {
        this.calorias = (4 * this.carboidratos) + (4 * this.proteinas) + (9 * this.gordura);
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
        return "Alimento{" + "id=" + id + ", nome=" + nome + ", carboidratos=" + carboidratos + ", proteinas=" + proteinas + ", gordura=" + gordura + ", calorias=" + calorias + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
    }
    
    
    
}
