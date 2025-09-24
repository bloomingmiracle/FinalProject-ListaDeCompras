package listacompras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorListaCompras {
    private ArrayList<Produto> produtos;
    private ArrayList<Compra> compras;

    public GerenciadorListaCompras() {
        this.produtos = new ArrayList<>();
        this.compras = new ArrayList<>();
    }


    public void adicionarProduto(Produto p) {
        produtos.add(p);
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public boolean atualizarPrecoPlanejado(String nomeProduto, double novoPreco) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeProduto)) {
                p.setPrecoPlanejado(novoPreco);
                return true;
            }
        }
        return false;
    }


    public void registrarCompra(Compra c) throws ProductNotFoundException {
        boolean existe = false;
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(c.getNomeProduto())) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            throw new ProductNotFoundException("Produto não cadastrado: " + c.getNomeProduto());
        }

        compras.add(c);
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }


    public double calcularTotalMes(String mes) {
        if (mes == null) return 0;
        double total = 0.0;

        for (Compra c : compras) {
            if (mes.equals(c.getMes())) {
                double valor = c.getQuantidadeComprada() * c.getPrecoUnitario();
                total += valor;
            }
        }
        return total;
    }

    public String getMenorPrecoDetalhado(String nomeProduto) {
        double menor = Double.MAX_VALUE;
        Compra compraMenor = null;

        for (Compra c : compras) {
            if (nomeProduto.equalsIgnoreCase(c.getNomeProduto())) {
                if (c.getPrecoUnitario() < menor) {
                    menor = c.getPrecoUnitario();
                    compraMenor = c;
                }
            }
        }

        if (compraMenor == null) {
            return "Nenhuma compra encontrada para " + nomeProduto;
        }

        return "Menor preço de " + nomeProduto + " foi R$ " + menor +
                " em " + compraMenor.getSupermercado() +
                " no mês " + compraMenor.getMes();
    }

    public String getMaiorPrecoDetalhado(String nomeProduto) {
        double maior = Double.MIN_VALUE;
        Compra compraMaior = null;

        for (Compra c : compras) {
            if (nomeProduto.equalsIgnoreCase(c.getNomeProduto())) {
                if (c.getPrecoUnitario() > maior) {
                    maior = c.getPrecoUnitario();
                    compraMaior = c;
                }
            }
        }

        if (compraMaior == null) {
            return "Nenhuma compra encontrada para " + nomeProduto;
        }

        return "Maior preço de " + nomeProduto + " foi R$ " + maior +
                " em " + compraMaior.getSupermercado() +
                " no mês " + compraMaior.getMes();
    }

    public String supermercadoMaisBarato(String mes) {
        if (mes == null) return "Mês inválido";

        Map<String, Integer> vitorias = new HashMap<>();
        String supermercadoMaisBarato = null;
        int maxVitorias = 0;

        for (Produto p : produtos) {
            double menorPreco = Double.MAX_VALUE;
            String supermercadoVencedor = null;

            for (Compra c : compras) {
                if (mes.equals(c.getMes()) && p.getNome().equalsIgnoreCase(c.getNomeProduto())) {
                    if (c.getPrecoUnitario() < menorPreco) {
                        menorPreco = c.getPrecoUnitario();
                        supermercadoVencedor = c.getSupermercado();
                    }
                }
            }

            if (supermercadoVencedor != null) {
                vitorias.put(supermercadoVencedor, vitorias.getOrDefault(supermercadoVencedor, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : vitorias.entrySet()) {
            if (entry.getValue() > maxVitorias) {
                maxVitorias = entry.getValue();
                supermercadoMaisBarato = entry.getKey();
            }
        }

        if (supermercadoMaisBarato == null) {
            return "Nenhum supermercado encontrado";
        }

        double menorPreco = Double.MAX_VALUE;
        String produtoCampeao = null;
        for (Compra c : compras) {
            if (mes.equals(c.getMes()) && c.getPrecoUnitario() < menorPreco) {
                menorPreco = c.getPrecoUnitario();
                produtoCampeao = c.getNomeProduto();
            }
        }

        return supermercadoMaisBarato + " venceu com " + produtoCampeao +
                " a R$ " + menorPreco;
    }

    public String supermercadoMaisCaro(String mes) {
        if (mes == null) return "Mês inválido";

        Map<String, Integer> vitorias = new HashMap<>();
        String supermercadoMaisCaro = null;
        int maxVitorias = 0;

        for (Produto p : produtos) {
            double maiorPreco = Double.MIN_VALUE;
            String supermercadoVencedor = null;

            for (Compra c : compras) {
                if (mes.equals(c.getMes()) && p.getNome().equalsIgnoreCase(c.getNomeProduto())) {
                    if (c.getPrecoUnitario() > maiorPreco) {
                        maiorPreco = c.getPrecoUnitario();
                        supermercadoVencedor = c.getSupermercado();
                    }
                }
            }

            if (supermercadoVencedor != null) {
                vitorias.put(supermercadoVencedor, vitorias.getOrDefault(supermercadoVencedor, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : vitorias.entrySet()) {
            if (entry.getValue() > maxVitorias) {
                maxVitorias = entry.getValue();
                supermercadoMaisCaro = entry.getKey();
            }
        }

        if (supermercadoMaisCaro == null) {
            return "Nenhum supermercado encontrado";
        }

        double maiorPreco = Double.MIN_VALUE;
        String produtoCampeao = null;
        for (Compra c : compras) {
            if (mes.equals(c.getMes()) && c.getPrecoUnitario() > maiorPreco) {
                maiorPreco = c.getPrecoUnitario();
                produtoCampeao = c.getNomeProduto();
            }
        }

        return supermercadoMaisCaro + " venceu com " + produtoCampeao +
                " a R$ " + maiorPreco;
    }


    public void compararPrecoPlanejado(String nomeProduto, String mes) {
        double gastoReal = 0.0;
        double qtdComprada = 0.0;
        double precoPlanejado = 0.0;

        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nomeProduto)) {
                precoPlanejado = p.getPrecoPlanejado();
            }
        }

        for (Compra c : compras) {
            if (c.getNomeProduto().equalsIgnoreCase(nomeProduto) && mes.equals(c.getMes())) {
                gastoReal += c.getPrecoUnitario() * c.getQuantidadeComprada();
                qtdComprada += c.getQuantidadeComprada();
            }
        }

        if (qtdComprada > 0) {
            double mediaReal = gastoReal / qtdComprada;
            System.out.println("Análise de " + nomeProduto + " em " + mes);
            System.out.println("Preço planejado: R$ " + precoPlanejado);
            System.out.println("Preço médio pago: R$ " + mediaReal);
            if (mediaReal > precoPlanejado) {
                System.out.println("Você pagou acima do planejado.");
            } else {
                System.out.println("Você pagou dentro do planejado.");
            }
        } else {
            System.out.println("Nenhuma compra encontrada de " + nomeProduto + " em " + mes);
        }
    }
}

