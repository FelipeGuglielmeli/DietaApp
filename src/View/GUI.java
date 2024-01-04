/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Models.Alimento;
import Models.AvaliacaoFisica;
import Models.Pessoa;
import Models.Refeicao;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author felip
 */
public class GUI {
    Scanner scanner = new Scanner(System.in);
 
    public int menuLogin() {
        
        StringBuilder builder = new StringBuilder("");
        
        builder.append("Seja bem vindo ao programa!\n\n");
        builder.append("0 - Encerrar programa.\n");
        builder.append("1 - Login\n");
        builder.append("2 - Cadastrar\n");
        builder.append("3 - Mostrar todos usuarios cadastrados\n");
        
        System.out.print(builder.toString());
        
        return Integer.parseInt(scanner.nextLine());
    }
    
    public Alimento cadastroAlimento(){
        Alimento a = new Alimento();
        System.out.println("\nInsira o nome do alimento:");
        a.setNome(scanner.nextLine());
        System.out.println("\nInsira a quantidade de carboidratos:");
        a.setCarboidratos(Float.parseFloat(scanner.nextLine()));
        System.out.println("\nInsira a quantidade de gorduras:");
        a.setGordura(Float.parseFloat(scanner.nextLine()));
        System.out.println("\nInsira a quantidade de proteinas:");
        a.setProteinas(Float.parseFloat(scanner.nextLine()));
        
        return a;
    }
    
    public List<String> cadastraPreferencias(){
        List<String> pref = new ArrayList<>();
        
        for (int i = 1; i <= 8; i++){
            System.out.println("\nInsira o alimento numero "+ i +":");
            pref.add(scanner.nextLine());
        }
        
        return pref;
    }
    
    public Pessoa cadastroPessoa() {
        
        Pessoa p = new Pessoa();
        
        System.out.println("\nInsira o nome:");
        p.setNome(scanner.nextLine());
        System.out.println("\nInsira o login:");
        p.setLogin(scanner.nextLine());
        System.out.println("\nInsira a senha:");
        p.setSenha(scanner.nextLine());
        System.out.println("\nInsira o sexo:");
        p.setSexo(scanner.nextLine());
        LocalDateTime dataNasc = LocalDateTime.now();
        p.setData_nascimento(dataNasc);
  
        return p;
    }
    
    public Alimento cadastraAlimento(){
        Alimento alimento = new Alimento();
        
        System.out.println("Informe o nome do alimento:");
        alimento.setNome(scanner.nextLine());
        System.out.println("\nInforme a quantidade de carboidratos:");
        alimento.setCarboidratos(Float.parseFloat(scanner.nextLine()));
        System.out.println("\nInforme a quantidade de proteinas:");
        alimento.setProteinas(Float.parseFloat(scanner.nextLine()));
        System.out.println("\nInforme a quantidade de gorduras:");
        alimento.setGordura(Float.parseFloat(scanner.nextLine()));
        
        return alimento;
    }
    
    public void cadastraMedidas(AvaliacaoFisica avaliacao) {
   
        System.out.println("\nInforme o peso:");
        avaliacao.setPeso(Float.parseFloat(scanner.nextLine()));
        System.out.println("\nInforme a altura:");
        avaliacao.setAltura(Float.parseFloat(scanner.nextLine()));
        System.out.println("\nInforme o pescoco:");
        avaliacao.setPescoco(Float.parseFloat(scanner.nextLine()));
        System.out.println("\nInforme a idade:");
        avaliacao.setIdade(Float.parseFloat(scanner.nextLine()));
        System.out.println("\nInforme o quadril:");
        avaliacao.setQuadril(Float.parseFloat(scanner.nextLine()));       
        System.out.println("\nInforme a cintura:");
        avaliacao.setCintura(Float.parseFloat(scanner.nextLine()));
        System.out.println("\nInforme o fator de atividade:");
        avaliacao.setFatorAtividade(scanner.nextLine());       
                 
    }
    
    public int menuAdmin(){
        StringBuilder builder = new StringBuilder("");
        builder.append("1 - Localizar usuario\n");
        builder.append("2 - Mostrar todos usuarios\n");
        builder.append("3 - Fazer logout.\n");
        System.out.print(builder.toString());
        
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuPaciente() {
        StringBuilder builder = new StringBuilder("");
        builder.append("1 - Buscar dieta\n");
        builder.append("2 - Visualizar avaliação fisica\n");
        builder.append("3 - Inserir preferencias de alimentos\n");
        builder.append("4 - Alterar a lista de preferencias\n");
        builder.append("5 - Fazer logout.\n");
        System.out.print(builder.toString());
        
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuNutricionista(){
        StringBuilder builder = new StringBuilder("");
        builder.append("1 - Buscar paciente\n");      
        builder.append("2 - Cadastrar paciente\n");
        builder.append("3 - Cadastrar alimento\n");
        builder.append("4 - Excluir alimento\n");
        builder.append("5 - Fazer logout.\n");
        System.out.print(builder.toString());
        
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuPacienteSelecionado() {
        StringBuilder builder = new StringBuilder("");       
        builder.append("1 - Gerar avaliação fisica do paciente\n");
        builder.append("2 - Atualizar avaliação fisica do paciente\n");
        builder.append("3 - Cadastrar dieta do paciente\n");
        builder.append("4 - Excluir paciente\n");
        builder.append("5 - Voltar ao menu anterior.\n");
        System.out.print(builder.toString());
        
        return Integer.parseInt(scanner.nextLine());
    }
    
    public int menuAdminSelecionado() {
        StringBuilder builder = new StringBuilder("");
        builder.append("1 - Alterar atribuição de usuario.");
        builder.append("2 - Excluir usuario.");   
        builder.append("3 - Voltar."); 
        
        return Integer.parseInt(scanner.nextLine());
    }  
    
    public Refeicao criarRefeicao(){
        Refeicao refeicao = new Refeicao();
        String nome;
        int porcao;        
        
        System.out.println("Informe qual será a refeição:");
        refeicao.setNome(scanner.nextLine());
        for(int i = 1; i <= 4; i++){
            System.out.println("Informe o alimento numero " + i + ":");
            nome = scanner.nextLine();
            System.out.println("Informe a porcao do alimento numero " + i + ":");
            porcao = Integer.parseInt(scanner.nextLine());
            refeicao.getAlimentos().put(nome, porcao);
        }
             
        return refeicao;
    }
    
}
