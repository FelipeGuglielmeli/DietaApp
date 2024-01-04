
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author felip
 */
public class Dieta {
    private int id; 
    private String nome;
    private float carboidrato;
    private float proteina;
    private float gordura;
    private float calorias;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<Integer> refeicoesId = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    private void setCalorias(){
        this.calorias = (4 * this.carboidrato) + (4 * this.proteina) + (9 * this.gordura);
    }
    
    public void setMacros(List<Refeicao> refeicoes){
        int i = 0;
        
        for (Refeicao refeicao : refeicoes){
            this.gordura += refeicoes.get(i).getGordura();
            this.carboidrato += refeicoes.get(i).getCarboidrato();
            this.proteina += refeicoes.get(i).getProteina();
            i++;
        }
        
        this.setCalorias();
    }
    
    public void setCarboidrato(Float carboidrato){
        this.carboidrato = carboidrato;
    }

    public float getCarboidrato() {
        return carboidrato;
    }
    
    public void setProteina(Float proteina){
        this.proteina = proteina;
    }

    public float getProteina() {
        return proteina;
    }
    
    public void setGordura(Float gordura){
        this.gordura = gordura;
    }

    public float getGordura() {
        return gordura;
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

    public List<Integer> getRefeicoesId() {
        return refeicoesId;
    }

    public void setRefeicoesId(List<Integer> refeicoesId) {
        this.refeicoesId = refeicoesId;
    }

    @Override
    public String toString() {
        return "Dieta{" + "id=" + id + ", nome=" + nome + ", carboidrato=" + carboidrato + ", proteina=" + proteina + ", gordura=" + gordura + ", createDate=" + createDate + ", updateDate=" + updateDate + ", refeicoesId=" + refeicoesId + '}';
    }
}
