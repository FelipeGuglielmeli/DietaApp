/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAOs.AlimentoDAO;
import DAOs.AvaliacaoFisicaDAO;
import DAOs.DietaDAO;
import DAOs.PessoaDAO;
import DAOs.PreferenciasDAO;
import DAOs.RefeicaoDAO;
import Models.AvaliacaoFisica;
import Models.Dieta;
import Models.Pessoa;
import Models.Preferencias;
import View.GUI;
import java.util.Scanner;

/**
 *
 * @author felip
 */
public class Controller {
    PessoaDAO pessoasDAO = new PessoaDAO();
    AvaliacaoFisicaDAO avaliacaoDAO = new AvaliacaoFisicaDAO();
    DietaDAO dietaDAO = new DietaDAO();
    RefeicaoDAO refeicaoDAO = new RefeicaoDAO();
    AlimentoDAO alimentoDAO = new AlimentoDAO();
    PreferenciasDAO preferenciasDAO = new PreferenciasDAO();
    GUI gui = new GUI();

    public Controller() {
        int opcaoUser = 10;
        Pessoa logado;
        Pessoa paciente;
        AvaliacaoFisica avaliacao = new AvaliacaoFisica();
        Dieta dieta = new Dieta();
        Preferencias pref = new Preferencias();
        Scanner scanner = new Scanner(System.in);

        while (opcaoUser != 0) {
            opcaoUser = gui.menuLogin();

            switch (opcaoUser) {
                case 0:
                    System.out.println("\nEncerrando o programa!");
                    opcaoUser = 0;
                    break;
                case 1:
                    System.out.println("\nLogin:");
                    String login = scanner.nextLine();
                    System.out.println("\nSenha:");
                    String senha = scanner.nextLine();
                    logado = pessoasDAO.buscaLogin(login, senha);
                    System.out.println(logado.toString()); ;

                    if ("paciente".equals(logado.getTipoUsuario())) {
                        while (opcaoUser != 5) {
                            opcaoUser = gui.menuPaciente();

                            switch (opcaoUser) {
                                case 1:
                                    //implementar busca de dieta
                                    System.out.println(dietaDAO.buscaPorNome(logado.getNome()));
                                    break;
                                case 2:
                                    //implementar busca de avalicao fisica
                                    avaliacao = avaliacaoDAO.buscaPorNome(logado.getNome());
                                    avaliacao.calculaIndices(logado.getSexo());
                                    System.out.println(avaliacao.toString());                                   
                                    break;
                                case 3:
                                    //implementar inserção de preferencias  
                                    pref.setNome(logado.getNome());
                                    pref.setPreferencias(gui.cadastraPreferencias());
                                    if (preferenciasDAO.Adiciona(pref))
                                        System.out.println("Preferencias cadastradas com sucesso!");
                                    break;
                                case 4:
                                    //implementar alteração de preferencias
                                    pref = preferenciasDAO.buscaPorNome(logado.getNome());
                                    pref.setPreferencias(gui.cadastraPreferencias());
                                    if (preferenciasDAO.altera(pref))
                                        System.out.println("Preferencias alterada com sucesso!");
                                    break;
                                case 5:
                                    break;
                            }
                        }
                    } else if ("nutricionista".equals(logado.getTipoUsuario())) {
                        while (opcaoUser != 5) {
                            //System.out.println("\nentrou menu nutri");
                            opcaoUser = gui.menuNutricionista();

                            switch (opcaoUser) {
                                case 1:
                                    System.out.println("\nIsnira o nome do paciente:");
                                    String nome = scanner.nextLine();
                                    paciente = pessoasDAO.buscaPorNome(nome);
                                    if (paciente == null || !"paciente".equals(paciente.getTipoUsuario())) {
                                        System.out.println("\nPaciente não encontrado.");
                                    } else {
                                        while (opcaoUser != 4) {
                                            opcaoUser = gui.menuPacienteSelecionado();

                                            switch (opcaoUser) {
                                                case 1:
                                                    //implementar insercao de avaliacao
                                                    avaliacao.setNome(paciente.getNome());
                                                    gui.cadastraMedidas(avaliacao);
                                                    avaliacao.calculaIndices(paciente.getSexo());
                                                    if (avaliacaoDAO.adiciona(avaliacao))
                                                        System.out.println("Avaliação gerada com sucesso");
                                                    System.out.println(avaliacao.toString());
                                                    break;
                                                case 2:
                                                    //implementar atualizacao de avaliacao                            
                                                    avaliacao.setNome(paciente.getNome());
                                                    System.out.println("\nInsira as novas medidas do paciente para alterar a avaliação.");
                                                    gui.cadastraMedidas(avaliacao);
                                                    avaliacao.calculaIndices(paciente.getSexo());
                                                    
                                                    if (avaliacaoDAO.Altera(avaliacao))
                                                        System.out.println("Avaliação alterada com sucesso");
                                                    System.out.println(avaliacao.toString());
                                                    break;
                                                case 3:
                                                    //implementar insercao de dieta
                                                    dieta.setNome(paciente.getNome());
                                                    System.out.println("Cadastrar primeira refeicao da dieta:");
                                                    dieta.getRefeicoesId().add(refeicaoDAO.adiciona(gui.criarRefeicao()));
                                                    System.out.println("Cadastrar segunda refeicao da dieta:");
                                                    dieta.getRefeicoesId().add(refeicaoDAO.adiciona(gui.criarRefeicao()));
                                                    System.out.println("Cadastrar terceira refeicao da dieta:");
                                                    dieta.getRefeicoesId().add(refeicaoDAO.adiciona(gui.criarRefeicao()));
                                                    System.out.println("Cadastrar quarta refeicao da dieta:");
                                                    dieta.getRefeicoesId().add(refeicaoDAO.adiciona(gui.criarRefeicao()));
                                                    
                                                    dieta.setMacros(dietaDAO.retornaRefeicoes(dieta));
                                                    
                                                    if (dietaDAO.adiciona(dieta))
                                                        System.out.println("Dieta adicionada com sucesso");
                                                    
                                                    System.out.println(dieta.toString());
                                                    break; 
                                                case 4:
                                                    //implementar exclusao de paciente
                                                    if(pessoasDAO.remove(paciente.getNome()))
                                                        System.out.println("\nPaciente removido com sucesso.");
                                                    break;
                                                case 5:
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                case 2:
                                    //implementar cadastro de paciente
                                    System.out.println("Informe os dados do paciente:");
                                    if(pessoasDAO.adiciona(gui.cadastroPessoa()));
                                        System.out.println("Paciente inserido com sucesso.");
                                    break;
                                case 3:
                                    //implementar cadastro de alimentos
                                    System.out.println("Insira os dados do alimento:");
                                    if(alimentoDAO.adiciona(gui.cadastraAlimento()))
                                        System.out.println("Alimento inserido com sucesso.");
                                    break;
                                case 4:
                                    //implementar exclusao de alimentos
                                    System.out.println("Informe o alimento que deseja excluir:");
                                    if(alimentoDAO.remove(scanner.nextLine()));
                                        System.out.println("Alimento removido com sucesso.");
                                    break;
                                case 5:
                                    break;
                            }
                        }
                    } else {
                        while (opcaoUser != 3) {
                            opcaoUser = gui.menuAdmin();

                            switch (opcaoUser) {
                                case 1:
                                    System.out.println("\nIsnira o nome do usuario:");
                                    String nome = scanner.nextLine();
                                    paciente = pessoasDAO.buscaPorNome(nome);
                                    if (paciente == null) {
                                        System.out.println("\nUsuário não encontrado.");
                                    } else {
                                        while (opcaoUser != 3) {
                                            opcaoUser = gui.menuAdminSelecionado();

                                            switch (opcaoUser) {
                                                case 1:
                                                    System.out.println("\nQual o tipo de usuario que será atribuido?");
                                                    String tipoUsuario = scanner.nextLine();
                                                    if (pessoasDAO.alteraTipoUsuario(nome, tipoUsuario))
                                                        System.out.println("\nTipo de usuario alterado com sucesso.");
                                                    break;
                                                case 2:
                                                    //implementar exclusao de usuario
                                                    if(pessoasDAO.remove(nome))
                                                        System.out.println("\nUsuario removido com sucesso.");
                                                    break;
                                                case 3:
                                                    break;                                                                                           
                                            }
                                        }
                                    }                                   
                                    break;
                                case 2:
                                    pessoasDAO.mostraTodos();
                                    break;
                                case 3:
                                    break;
                                
                            }
                        }
                    }
                    break;
                case 2:
                    //implementar cadastro de usuario
                    System.out.println("Informe os dados do usuario:");
                    if(pessoasDAO.adiciona(gui.cadastroPessoa()));
                        System.out.println("Usuario inserido com sucesso.");
                    break;
                case 3:
                    System.out.println("\n\n");
                    pessoasDAO.mostraTodos();
            }
        }
    }

    public void buscaPessoa(String nome) {
        Pessoa p;
        p = pessoasDAO.buscaPorNome(nome);

        if (p == null) {
            System.out.println("Pessoa não encontrada.");
        } else {
            System.out.println(p.toString());
        }
    }

    public void removerPessoa(String nome ) {
        if (pessoasDAO.remove(nome)) {
            System.out.println("Pessoa removida com sucesso.");
        } else {
            System.out.println("Pessoa não encontrada.");
        }
    }

    public static void main(String[] args) {
        new Controller();
    }
}
