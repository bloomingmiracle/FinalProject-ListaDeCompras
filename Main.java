package listacompras;

import java.util.Scanner;
//Main
public class Main {
    private static int lerInteiro(Scanner sc, String mensagem) {
        while(true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException var3) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(Scanner sc, String mensagem) {
        while(true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException var3) {
                System.out.println("Entrada inválida. Digite um número decimal (ex: 5.0).");
            }
        }
    }

    public static void main(String[] args) {
        GerenciadorListaCompras g1 = new GerenciadorListaCompras();
        Scanner sc = new Scanner(System.in);

        int opcao;
        do {
            //Menu Options
            System.out.println("\n=== MENU LISTA DE COMPRAS ===");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Registrar Compra");
            System.out.println("3 - Listar Produtos");
            System.out.println("4 - Listar Compras");
            System.out.println("5 - Calcular Total do Mês");
            System.out.println("6 - Menor Preço de um Produto");
            System.out.println("7 - Maior Preço de um Produto");
            System.out.println("8 - Supermercado Mais Barato do Mês");
            System.out.println("9 - Comparar Preço Planejado x Real");
            System.out.println("10 - Atualizar Preço Planejado de Produto");
            System.out.println("11 - Supermercado Mais Caro do Mês");
            System.out.println("0 - Sair");
            opcao = lerInteiro(sc, "Escolha uma opção: ");
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;

                case 1:
                    int qtdProdutos = lerInteiro(sc, "Quantos produtos deseja adicionar? ");

                    for(int i = 0; i < qtdProdutos; ++i) {
                        System.out.println("\nProduto " + (i + 1) + ":");
                        System.out.print("Nome do produto: ");
                        String nome = sc.nextLine();
                        System.out.print("Unidade (ex: kg, L): ");
                        String unidade = sc.nextLine();
                        double qtdPlan = lerDouble(sc, "Quantidade planejada: ");
                        double precoPlan = lerDouble(sc, "Preço planejado por unidade: ");
                        g1.adicionarProduto(new Produto(nome, unidade, qtdPlan, precoPlan));
                    }

                    System.out.println("Produtos adicionados com sucesso!");
                    break;

                case 2:
                    int qtdCompras = lerInteiro(sc, "Quantas compras deseja registrar? ");

                    for(int i = 0; i < qtdCompras; ++i) {
                        System.out.println("\nCompra " + (i + 1) + ":");

                        try {
                            System.out.print("Mês (formato AAAA-MM): ");
                            String mes = sc.nextLine();
                            System.out.print("Nome do produto: ");
                            String nomeProd = sc.nextLine();
                            double qtd = lerDouble(sc, "Quantidade comprada: ");
                            double preco = lerDouble(sc, "Preço unitário: ");
                            System.out.print("Supermercado: ");
                            String mercado = sc.nextLine();
                            g1.registrarCompra(new Compra(mes, nomeProd, qtd, preco, mercado));
                            System.out.println("Compra registrada com sucesso!");
                        } catch (ProductNotFoundException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n=== Produtos cadastrados ===");
                    System.out.printf("%-15s %-10s %-15s %-15s%n", "Nome", "Unidade", "Qtd Planejada", "Preço Planejado");

                    for(Produto p : g1.getProdutos()) {
                        System.out.printf("%-15s %-10s %-15.2f %-15.2f%n", p.getNome(), p.getUnidade(), p.getQuantidadePlanejada(), p.getPrecoPlanejado());
                    }
                    break;

                case 4:
                    System.out.println("\n=== Compras registradas ===");
                    System.out.printf("%-10s %-15s %-10s %-10s %-15s%n", "Mês", "Produto", "Qtd", "Preço", "Supermercado");

                    for(Compra c : g1.getCompras()) {
                        System.out.printf("%-10s %-15s %-10.2f %-10.2f %-15s%n", c.getMes(), c.getNomeProduto(), c.getQuantidadeComprada(), c.getPrecoUnitario(), c.getSupermercado());
                    }
                    break;

                case 5:
                    System.out.print("Digite o mês (AAAA-MM): ");
                    String mesTotal = sc.nextLine();
                    double total = g1.calcularTotalMes(mesTotal);
                    System.out.println("Total gasto em " + mesTotal + ": R$ " + total);
                    break;
                case 6:
                    System.out.print("Nome do produto: ");
                    String prodMenor = sc.nextLine();
                    System.out.println(g1.getMenorPrecoDetalhado(prodMenor));
                    break;
                case 7:
                    System.out.print("Nome do produto: ");
                    String prodMaior = sc.nextLine();
                    System.out.println(g1.getMaiorPrecoDetalhado(prodMaior));
                    break;
                case 8:
                    System.out.print("Digite o mês (AAAA-MM): ");
                    String mesBarato = sc.nextLine();
                    System.out.println("Supermercado mais barato em " + mesBarato + ": " + g1.supermercadoMaisBarato(mesBarato));
                    break;
                case 9:
                    System.out.print("Digite o nome do produto: ");
                    String nomeAnalise = sc.nextLine();
                    System.out.print("Digite o mês (YYYY-MM): ");
                    String mesAnalise = sc.nextLine();
                    g1.compararPrecoPlanejado(nomeAnalise, mesAnalise);
                    break;
                case 10:
                    System.out.print("Digite o nome do produto para atualizar: ");
                    String nomeUpdate = sc.nextLine();
                    double novoPreco = lerDouble(sc, "Novo preço planejado: ");
                    if (g1.atualizarPrecoPlanejado(nomeUpdate, novoPreco)) {
                        System.out.println("Preço planejado atualizado com sucesso!");
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;
                case 11:
                    System.out.print("Digite o mês (AAAA-MM): ");
                    String mesCaro = sc.nextLine();
                    System.out.println("Supermercado mais caro em " + mesCaro + ": " + g1.supermercadoMaisCaro(mesCaro));
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while(opcao != 0);

        sc.close();
    }
}
