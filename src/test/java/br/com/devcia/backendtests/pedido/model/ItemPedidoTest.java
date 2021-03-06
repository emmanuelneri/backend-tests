package br.com.devcia.backendtests.pedido.model;

import br.com.devcia.backendtests.pedido.exception.AlteracaoItemPedidoInvalidaException;
import br.com.devcia.backendtests.pedido.exception.QuantidadeInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ItemPedidoTest {

    @Test
    @DisplayName("Deve retornar o valor total zero ao chamar método getTotal com item vazio")
    public void deveRetornarValorTotalZero() {
        Assertions.assertEquals(BigDecimal.ZERO, new ItemPedido().getTotal());
    }

    @Test
    @DisplayName("Deve retornar o valor total igual ao valor do item quando tiver apenas uma quantidade")
    public void deveRetornarValorTotalIgualValorComUmaQuantidade() {
        final ItemPedido itemPedido = new ItemPedido(BigDecimal.TEN, 1);
        itemPedido.calculaTotal();
        Assertions.assertEquals(BigDecimal.TEN, itemPedido.getTotal());
    }

    @Test
    @DisplayName("Deve retornar o valor total resultado da multiplicação do valor do item pela quantidade")
    public void deveRetornarValorTotalMultiplicandoOValorDoItemPelaQuantidade() {
        final ItemPedido itemPedido = new ItemPedido(BigDecimal.TEN, 2);
        itemPedido.calculaTotal();
        Assertions.assertEquals(BigDecimal.valueOf(20), itemPedido.getTotal());
    }

    @Test
    @DisplayName("Deve lançar AlteracaoItemPedidoInvalidaException ao alterar a quantidade do item para uma quantidade negativa")
    public void deveLancarErroDeAlteracaoInvalidaAoAlterarQuantidadeDoItemParaUmaQuantidadeNegativa() {
        Assertions.assertThrows(AlteracaoItemPedidoInvalidaException.class, () -> {
            ItemPedido itemPedido = new ItemPedido(BigDecimal.TEN, 2);
            itemPedido.setQuantidade(-1);
        });
    }

    @Test
    @DisplayName("Deve lançar QuantidadeInvalidaException ao alterar a quantidade do item para uma quantidade negativa")
    public void deveLancarErroDeQuantidadeInvalidaAoAlterarQuantidadeDoItemParaUmaQuantidadeNegativa() {
        Assertions.assertThrows(QuantidadeInvalidaException.class, () -> {
            ItemPedido itemPedido = new ItemPedido(BigDecimal.TEN, 2);
            itemPedido.setQuantidade(-10);
        });
    }

}