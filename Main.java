import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Robo> robos = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao = 0;
        robos.add(new Robo(1, "Flamengo", 20, 5));
        robos.add(new Robo(2, "Fluminense", 10, 10));
        robos.add(new Robo(3, "Cruzeiro", 12, 8));
        robos.add(new Robo(4, "Internacional", 19, 2));
        robos.add(new Robo(5, "Palmeiras", 22, 9));

        do{
            System.out.println("\n===== CAMPEONATO DE ROBOS =====");
            System.out.println("1 - Cadastrar Robo");
            System.out.println("2 - Consultar Robo");
            System.out.println("3 - Listar Robos");
            System.out.println("4 - Realizar Combate");
            System.out.println("5 - Recuperar energia do robô");
            System.out.println("6 - Excluir robô");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    System.out.println("\n===== CADASTRAR ROBO =====");

                    System.out.println("Código do robo: ");
                    int codigo = scanner.nextInt();

                    boolean codigoExiste = true;

                    while (codigo <= 0 || codigoExiste) { 
                        if (codigo <= 0) {
                            System.out.println("O código deve ser positivo.");
                        } 
                        else {
                            codigoExiste = false;
                            for (Robo robo : robos) {
                                if (robo.codigo == codigo) {
                                    codigoExiste = true;
                                }   
                            }
                            if (codigoExiste) {
                                System.out.println("O código já existe.");
                            }
                        }
                        if (codigo <= 0 || codigoExiste) {
                            System.out.println("Digite outro código: ");
                            codigo = scanner.nextInt();
                        }
                    }

                    System.out.println("Nome do Robo: ");
                    String nome = scanner.next();

                    System.out.print("Valor de ataque: ");
                    int ataque = scanner.nextInt();

                    if(ataque < 0 || ataque > 30){
                        System.out.println("O ataque deve ser entre 10 e 30");
                        System.out.println("Valor de ataque: ");
                        ataque = scanner.nextInt();
                    }

                    System.out.print("Valor de defesa: ");
                    int defesa = scanner.nextInt();

                    if(defesa < 0 || defesa > 20){
                        System.out.println("A defesa deve ser entre 0 e 20");
                        System.out.println("Valor de defesa: ");
                        defesa = scanner.nextInt();
                    }

                    Robo novoRobo = new Robo(codigo, nome, ataque, defesa);
                    robos.add(novoRobo);

                    System.out.println("Robo cadastrado com sucesso!");
                    break;

                    case 2:
                    System.out.println("\n===== CONSULTAR ROBO =====");
                    System.out.println("Digite o código do robo que deseja buscar: ");
                    int buscaCodigo = scanner.nextInt();
                    
                    boolean encontrado = false; 

                    for (Robo robo : robos) {
                        if (robo.codigo == buscaCodigo) {
                            System.out.println("\n===========================");
                            robo.ExibirSituacao();
                            System.out.println("\n===========================");
                            encontrado = true;
                            break;
                        }
                    } if (!encontrado) {
                        System.out.println("Robô não encontrado.");
                    }
                    break;

                    case 3:
                    System.out.println("\n==== EXIBIR ROBOS ====");
                    for (Robo robo : robos) {
                        robo.ExibirSituacao();
                    System.out.println("\n===========================");
                    }
                    break;

                    case 4: 
                    System.out.println("\n==== COMBATE DE ROBOS ====");
                    Robo robo1 = solicitarRobo(scanner);
                    Robo robo2 = solicitarRobo(scanner);

                    Robo primeiro;
                    Robo segundo;
                    if (robo1.pontos == robo2.pontos) {
                        primeiro = robo1.codigo > robo2.codigo ? robo2 : robo1;
                        segundo = robo1.codigo > robo2.codigo ? robo1 : robo2;
                    } else if (robo1.pontos < robo2.pontos) {
                        primeiro = robo1;
                        segundo = robo2;
                    } else {
                        primeiro = robo2;
                        segundo = robo1;
                    }
                    
                    for (int i = 1; i <= 5; i++) {
                        System.out.println("Rodada " + i + "ª: ");
                        int dano = primeiro.ataque - segundo.defesa;
                        if (dano < 0) {
                            dano = 0;
                        } else if (dano < 5) {
                            dano = 5;
                        }

                        if (i % 2 == 0) {
                            dano += 5;
                        }

                        segundo.energiaAtual -= dano;
                        if (segundo.energiaAtual < 0) {
                            segundo.energiaAtual = 0;
                        }
                        if (segundo.energiaAtual == 0) {
                            break;
                        }

                        primeiro.ExibirSituacao();
                        System.out.println("\n===========================");
                        segundo.ExibirSituacao();

                        Robo swap = primeiro;
                        primeiro = segundo;
                        segundo = swap;

                        System.out.println("=============================");
                    }

                    if (primeiro.energiaAtual == segundo.energiaAtual) {
                        primeiro.pontos ++;
                        segundo.pontos ++;
                    } else if (primeiro.energiaAtual > segundo.energiaAtual) {
                        primeiro.pontos += 3;
                        primeiro.vitoria++;
                        segundo.derrota++;
                    } else {
                        segundo.pontos += 3;
                        segundo.vitoria++;
                        primeiro.derrota++;
                    }

                    break;

                    case 5:
                        System.out.println("\n==== RECUPERAR ENERGIA DO ROBO ====");
                        System.out.println("Digite o código do robo que deseja recuperar energia: ");
                        int codigoEnergia = scanner.nextInt();

                        boolean codigoEncontrado = false; 

                        Robo roboEnergia = null; 

                        for (Robo robo : robos) {
                        if (robo.codigo == codigoEnergia) {
                            codigoEncontrado = true;
                            roboEnergia = robo;

                            System.out.println("Digite quanto de energia deseja recuperar do robo: ");
                            int qtdeEnergia = scanner.nextInt();

                        if (qtdeEnergia <= 0) {
                            System.out.println("A quantidade deve ser positiva!");
                        } else if (qtdeEnergia % 10 != 0) {
                            System.out.println("A quantidade deve ser múltipla de 10!");
                        } else {
                            int custoPonto = qtdeEnergia / 10;
                            if(roboEnergia.pontos < custoPonto){
                                System.out.println("Deu Erro: Pontos Insuficientes.");
                            }else if(roboEnergia.energiaAtual + qtdeEnergia > 100){
                                System.out.println("O Robo não poderá ter mais que 100 de energia. ");
                            }else{
                                roboEnergia.RecuperaEnergia(qtdeEnergia);
                                System.out.println("A nova quantidade de energia do robo é de: "+roboEnergia.energiaAtual);
                            }                           
                        }
                        break;

                    }  }
                    if (!codigoEncontrado) {
                        System.out.println("Robô não encontrado.");
                    }
               
                    break;

                    case 6: 
                        System.out.println("\n ==== EXCLUIR ROBO ===");
                        System.out.println("Digite o código do robo que deseja exluir: ");
                        int codigoExclusao = scanner.nextInt();

                        boolean encontradoExclusao = false; 
                        Robo roboExcluir = null; 

                        for (Robo robo: robos){
                            if(robo.codigo == codigoExclusao){
                                encontradoExclusao = true;
                                roboExcluir = robo; 
                                break;
                            }
                        }
                         if(!encontradoExclusao){
                                System.out.println("Robô não encontrado.");
                            }else if(roboExcluir.vitoria > 0 || roboExcluir.derrota > 0){
                                 System.out.println("O robo não pode ser excluido pois ja participou de uma batalha. ");
                            }else{
                                robos.remove(roboExcluir);
                                roboExcluir.ExcluirRobo(codigoExclusao);
                                System.out.println("O Robo foi excluido com sucesso! ");

                            break;
                            }

                    break;

                    case 0: 
                    System.out.println("Programa encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
        scanner.close();
    }

    static Robo solicitarRobo(Scanner scanner) {
        Robo robo;
        int codigoBatalha = 0;
        do {
            System.out.println("Digite o código do robo: ");
            codigoBatalha = scanner.nextInt();
            robo = buscarRobo(codigoBatalha);
            if (robo == null) {
                System.out.println("Robo não encontrado! Digite novamente.");
            }
        } while (robo == null);

        return robo;
    }

    static Robo buscarRobo(int codigo) {
        for (Robo robo : robos) {
            if (robo.codigo == codigo) {
                return robo;
            }
        }

        return null;
    }
}
