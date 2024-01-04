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
public class AvaliacaoFisica {
    private long id; 
    private String nome;
    private float peso;
    private float altura;
    private float idade;
    private float pescoco;
    private float cintura;
    private float quadril;
    private float imc;
    private float tmb;
    private float bf;
    private float massaGorda;
    private float massaMagra;
    private float fatorAtividade;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    
    public AvaliacaoFisica() {
        createDate.now();
    }
    
    public void calculaIndices(String sexo){
        this.setImc();
        this.setTmb(sexo);
        this.setBf(sexo);
        this.setMassaGorda();
        this.setMassaMagra();
    }

    public long getId() {
        return id;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getIdade() {
        return idade;
    }

    public void setIdade(float idade) {
        this.idade = idade;
    }

    public float getPescoco() {
        return pescoco;
    }

    public void setPescoco(float pescoco) {
        this.pescoco = pescoco;
    }

    public float getCintura() {
        return cintura;
    }

    public void setCintura(float cintura) {
        this.cintura = cintura;
    }

    public float getQuadril() {
        return quadril;
    }

    public void setQuadril(float quadril) {
        this.quadril = quadril;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getImc() {
        return imc;
    }

    public void setImc() {
        this.imc = this.peso / (this.altura * this.altura);
    }

    public float getTmb() {
        return tmb;
    }

    public void setTmb(String sexo) {
        if(sexo == "masculino"){
            this.tmb = (float) (this.fatorAtividade * (66 + ((13.7 * this.peso) + (5 * (this.altura * 100)) - (6.8 * this.idade))));
        } else {
            this.tmb = (float) (this.fatorAtividade * (655 + ((9.6 * this.peso) + (1.8 * (this.altura * 100)) - (4.7 * this.idade))));
        }
    }

    public float getBf() {
        return bf;
    }

    public void setBf(String sexo) {
        if (sexo == "masculino"){
            this.bf = (float) (86.010 * Math.log10(this.cintura - this.pescoco) - 70.041 * Math.log10(this.altura * 100) + 36.76);
        } else {
            this.bf = (float) (163.205 * Math.log10(this.cintura + this.quadril - this.pescoco) - 97.684 * Math.log10(this.altura * 100) - 78.387);
        }
    }

    public float getMassaGorda() {
        return massaGorda;
    }

    public void setMassaGorda() {
        this.massaGorda = this.peso * (this.bf /100);
    }

    public float getMassaMagra() {
        return massaMagra;
    }

    public void setMassaMagra() {
        this.massaMagra = this.peso - this.massaGorda;
    }

    public float getFatorAtividade() {
        return fatorAtividade;
    }

    public void setFatorAtividade(String atividade) {
        if("sedentario".equals(atividade)){
            this.fatorAtividade = (float) 1.2;
        } else if("levemente ativo".equals(atividade)){
            this.fatorAtividade = (float) 1.375;
        }else if("moderadamente ativo".equals(atividade)){
            this.fatorAtividade = (float) 1.55;
        }else if("muito ativo".equals(atividade)){
            this.fatorAtividade = (float) 1.725;
        }else {
            this.fatorAtividade = (float) 1.9;
        }  
    }
    
    public void setFatorAtividade(float fatorAtividade){
        this.fatorAtividade = fatorAtividade;
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
        return "AvaliacaoFisica{" + "id=" + id + ", nome=" + nome + ", peso=" + peso + ", altura=" + altura + ", idade=" + idade + ", pescoco=" + pescoco + ", cintura=" + cintura + ", quadril=" + quadril + ", imc=" + imc + ", tmb=" + tmb + ", bf=" + bf + ", massaGorda=" + massaGorda + ", massaMagra=" + massaMagra + ", fatorAtividade=" + fatorAtividade + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
    }
    
}
