package br.com.devcia.backendtests.exception;

public class QuantidadeInvalidaException extends AlteracaoItemPedidoInvalidaException {

    private static final String QUANTIDADE_NEGATIVA = "A quantidade do item não pode ser alterada para uma quantidade negativa.";

    private QuantidadeInvalidaException(final String mensagem) {
        super(mensagem);
    }

    public static QuantidadeInvalidaException quantidadeNegativaException() {
        return new QuantidadeInvalidaException(QUANTIDADE_NEGATIVA);
    }
}
