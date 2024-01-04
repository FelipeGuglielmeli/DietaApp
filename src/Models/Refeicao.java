/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author felip
 */
public class Refeicao {
    private int id;
    private String nome;
    private float carboidrato;
    private float proteina;
    private float gordura;
    private float calorias;    
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Map<String, Integer> alimentos = new HashMap<>();

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
    
    private void setCalorias() {
        this.calorias = (4 * this.carboidrato) + (4 * this.proteina) + (9 * this.gordura);
    }
    
    public void setMacros(List<Alimento> alimentos){
        int i = 0, porcao = 0;
             
        for (Map.Entry<String, Integer> entry : this.getAlimentos().entrySet()){
            porcao = entry.getValue() - 100;
            if (porcao < 0){
                porcao = (porcao * (-1)) / 100;
                this.gordura += alimentos.get(i).getGordura() + (alimentos.get(i).getGordura() * porcao);
                this.proteina += alimentos.get(i).getProteinas() + (alimentos.get(i).getProteinas() * porcao);
                this.carboidrato += alimentos.get(i).getCarboidratos() + (alimentos.get(i).getCarboidratos() * porcao);
            } else {
                porcao = porcao / 100;
                this.gordura += alimentos.get(i).getGordura() + (alimentos.get(i).getGordura() * porcao);
                this.proteina += alimentos.get(i).getProteinas() + (alimentos.get(i).getProteinas() * porcao);
                this.carboidrato += alimentos.get(i).getCarboidratos() + (alimentos.get(i).getCarboidratos() * porcao);
            }
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

    public float getCalorias() {
        return calorias;
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

    public Map<String, Integer> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Map<String, Integer> alimentos) {
        this.alimentos = alimentos;
    }

    @Override
    public String toString() {
        return "Refeicao{" + "id=" + id + ", nome=" + nome + ", carboidrato=" + carboidrato + ", proteina=" + proteina + ", gordura=" + gordura + ", calorias=" + calorias + ", createDate=" + createDate + ", updateDate=" + updateDate + ", alimentos=" + alimentos + '}';
    }
}


