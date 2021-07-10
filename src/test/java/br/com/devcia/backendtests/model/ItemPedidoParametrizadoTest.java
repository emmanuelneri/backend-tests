package br.com.devcia.backendtests.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;

class ItemPedidoParametrizadoTest {

    public static Object[][] parametrosDeItems() {
        return new Object[][]{
                {new ItemPedido(), BigDecimal.ZERO},
                {new ItemPedido(BigDecimal.TEN, 1), BigDecimal.TEN},
                {new ItemPedido(BigDecimal.TEN, 2), BigDecimal.valueOf(20)},
                {new ItemPedido(BigDecimal.valueOf(10.50), 2), BigDecimal.valueOf(21.0)},
        };
    }

    @ParameterizedTest(name = "[{index}] Parâmetro: {0} | valor esperado: {1}")
    @MethodSource("parametrosDeItems")
    @DisplayName("Deve retornar o valor total resultado da multiplicação do valor do item pela quantidade")
    public void deveRetornarOValorTotal(final ItemPedido itemPedido, final BigDecimal valorEsperado) {
        Assertions.assertEquals(valorEsperado, itemPedido.getTotal());
    }

}