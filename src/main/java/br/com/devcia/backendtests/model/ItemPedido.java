package br.com.devcia.backendtests.model;

import br.com.devcia.backendtests.exception.QuantidadeInvalidaException;

import java.math.BigDecimal;

public class ItemPedido {

    private BigDecimal valor = BigDecimal.ZERO;
    private int quantidade;

    ItemPedido() {
    }

    ItemPedido(BigDecimal valor, int quantidade) {
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw QuantidadeInvalidaException.quantidadeNegativaException();
        }

        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return calculaTotal(this.valor, this.quantidade);
    }

    static BigDecimal calculaTotal(final BigDecimal valor, final int quantidade) {
        return valor.multiply(BigDecimal.valueOf(quantidade));
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "valor=" + valor +
                ", quantidade=" + quantidade +
                '}';
    }
}
