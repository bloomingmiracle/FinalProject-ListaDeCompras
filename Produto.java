package listacompras;

/*
 * The class Produto represents a product in the shopping list.
 * Each product has:
 * - a name (ex: Rice, Beans)
 * - a unit (ex: kg, L)
 * - a planned quantity (how much you want to buy)
 * - a planned price per unit (how much you expect to pay per unit)
 */
public class Produto {
    private String nome;
    private String unidade;
    private double quantidadePlanejada;
    private double precoPlanejado;

    // Constructor
    public Produto(String nome, String unidade, double quantidadePlanejada, double precoPlanejado) {
        this.nome = nome;
        this.unidade = unidade;
        this.quantidadePlanejada = quantidadePlanejada;
        this.precoPlanejado = precoPlanejado;
    }

    // Getters & Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }

    public double getQuantidadePlanejada() { return quantidadePlanejada; }
    public void setQuantidadePlanejada(double quantidadePlanejada) { this.quantidadePlanejada = quantidadePlanejada; }

    public double getPrecoPlanejado() { return precoPlanejado; }
    public void setPrecoPlanejado(double precoPlanejado) { this.precoPlanejado = precoPlanejado; }

    // Output in Text
    @Override
    public String toString() {
        return nome + " (" + unidade + ") planned: " + quantidadePlanejada +
                " | Planned price: R$ " + precoPlanejado;
    }
}
