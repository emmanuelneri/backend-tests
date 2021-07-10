package br.com.devcia.backendtests.pedido.model;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

class ItemPedidoDinamicoTest {

    private static final Faker FAKER = new Faker();
    private static final int QUANTIDADE_DE_TESTES = 50;
    private static final int QUANTIDADE_DE_CASAS_DECIMAIS_NO_VALOR = 2;
    private static final int VALOR_MINIMO_GERADO = 2;
    private static final int VALOR_MAXIMO_GERADO = 10_000;

    @TestFactory
    public Collection<DynamicTest> validarValorTotalComValoresAleatorios() {
        final Collection<DynamicTest> testes = new LinkedList<>();
        for (int i = 0; i < QUANTIDADE_DE_TESTES; i++) {
            final int quantidade = FAKER.number().randomDigitNotZero();
            final BigDecimal valor = BigDecimal.valueOf(FAKER
                    .number()
                    .randomDouble(QUANTIDADE_DE_CASAS_DECIMAIS_NO_VALOR, VALOR_MINIMO_GERADO, VALOR_MAXIMO_GERADO));

            final BigDecimal valorEsperado = calcularValorEsperado(valor, quantidade);
            testes.add(DynamicTest.dynamicTest(String.format("valor: %s - quantidade: %d |  total esperado: %s",
                    valor, quantidade, valorEsperado), executarTeste(valor, quantidade, valorEsperado)));
        }

        return testes;
    }

    private BigDecimal calcularValorEsperado(final BigDecimal valor, final int quantidade) {
        return valor.multiply(BigDecimal.valueOf(quantidade));
    }

    private Executable executarTeste(final BigDecimal valor, final int quantidade, BigDecimal valorEsperado) {
        return () -> {
            final ItemPedido itemPedido = new ItemPedido(valor, quantidade);
            Assertions.assertEquals(valorEsperado, itemPedido.getTotal());
        };
    }

}