package listacompras;

/**
 * The class **Compra** represents the purchases made
 * it includes *month, name of product, quantity to be
 * purchased, unit price, and the supermarket*
 */
public class Compra {
    private String mes;
    private String nomeProduto;
    private double quantidadeComprada;
    private double precoUnitario;
    private String supermercado;

    // Constructor
    public Compra(String mes, String nomeProduto, double quantidadeComprada,
                  double precoUnitario, String supermercado) {
        this.mes = mes;
        this.nomeProduto = nomeProduto;
        this.quantidadeComprada = quantidadeComprada;
        this.precoUnitario = precoUnitario;
        this.supermercado = supermercado;
    }

    // Getters & Setters
    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public double getQuantidadeComprada() { return quantidadeComprada; }
    public void setQuantidadeComprada(double quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }

    public double getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getSupermercado() { return supermercado; }
    public void setSupermercado(String supermercado) { this.supermercado = supermercado; }

    //Output in text
    @Override
    public String toString() {
        return mes + " | " + nomeProduto + " x" + quantidadeComprada +
                " @ R$ " + precoUnitario + " (" + supermercado + ")";
    }
}
