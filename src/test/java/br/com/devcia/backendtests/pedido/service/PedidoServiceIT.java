package br.com.devcia.backendtests.pedido.service;

import br.com.devcia.backendtests.cliente.model.Cliente;
import br.com.devcia.backendtests.pedido.model.ItemPedido;
import br.com.devcia.backendtests.pedido.model.Pedido;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Collections;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-embeddeddb.yml")
class PedidoServiceIT {

    private static final Faker FAKER = new Faker();

    @Autowired
    private PedidoService pedidoService;

    @Test
    @DisplayName("Deve Lançar erro ao salvar um pedido sem cliente")
    public void deveLancarErroAoSalvarPedidoSemCliente() {
        final Pedido pedido = new Pedido();
        final ItemPedido itemPedido = new ItemPedido();
        itemPedido.setValor(BigDecimal.TEN);
        itemPedido.setQuantidade(2);
        pedido.setItens(Collections.singletonList(itemPedido));
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> this.pedidoService.salvar(pedido));

    }

    @Test
    @DisplayName("Deve Lançar erro ao salvar um pedido sem item")
    public void deveLancarErroAoSalvarPedidoItem() {
        final Cliente cliente = new Cliente();
        cliente.setNome(FAKER.name().fullName());
        cliente.setDocumento(FAKER.number().digit());

        final Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        Assertions.assertThrows(ConstraintViolationException.class,
                () -> this.pedidoService.salvar(pedido));
    }

    @Test
    @DisplayName("Deve salvar um novo pedido também criando um novo cliente")
    public void deveSalvarUmNovoPedido() {
        final Cliente cliente = new Cliente();
        cliente.setNome("João");
        cliente.setDocumento("958.518.560-17");

        final Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        final ItemPedido itemPedido = new ItemPedido();
        itemPedido.setValor(BigDecimal.TEN);
        itemPedido.setQuantidade(2);
        itemPedido.setDescricao("Produto X");
        pedido.setItens(Collections.singletonList(itemPedido));

        final Pedido pedidoSalvo = this.pedidoService.salvar(pedido);
        Assertions.assertEquals(1, pedidoSalvo.getItens().size());
        Assertions.assertEquals(BigDecimal.valueOf(20), pedidoSalvo.getTotal());
        Assertions.assertNotNull(pedidoSalvo.getId());
    }

}
