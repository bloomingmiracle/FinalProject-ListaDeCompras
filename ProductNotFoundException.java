package listacompras;

/**
 * Exceção personalizada lançada quando
 * tenta-se registrar uma compra de um produto
 * que não está cadastrado.
 */
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
