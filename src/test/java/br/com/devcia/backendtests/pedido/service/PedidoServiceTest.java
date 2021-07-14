package br.com.devcia.backendtests.pedido.service;

import br.com.devcia.backendtests.cliente.model.Cliente;
import br.com.devcia.backendtests.cliente.service.ClienteService;
import br.com.devcia.backendtests.pedido.model.ItemPedido;
import br.com.devcia.backendtests.pedido.model.Pedido;
import br.com.devcia.backendtests.pedido.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    @DisplayName("Deve salvar um novo pedido, calculando valor total dos itens e do pedido")
    public void deveSalvarUmNovoPedido() {
        final Cliente cliente = criaCliente();
        final Pedido pedido = criaPedido(cliente);

        Mockito.when(clienteService.criarClienteSeNaoExistir(cliente)).thenReturn(cliente);
        Mockito.when(pedidoRepository.save(pedido)).then(invocationOnMock -> {
            final Pedido p = invocationOnMock.getArgument(0);
            p.setId(1L);
            return p;
        });

        final Pedido pedidoSalvo = this.pedidoService.salvar(pedido);
        Assertions.assertEquals(1, pedidoSalvo.getItens().size());
        Assertions.assertEquals(BigDecimal.valueOf(20), pedidoSalvo.getTotal());
        Assertions.assertNotNull(pedidoSalvo.getId());
    }

    @Test
    @DisplayName("Deveria falhar por salvar um pedido sem cliente, porém é executado porque a validação está 'mockada'")
    public void deveriaFalharPoremPassa() {
        final Pedido pedido = criaPedido(null);
        Mockito.when(pedidoRepository.save(pedido)).then(invocationOnMock -> {
            final Pedido p = invocationOnMock.getArgument(0);
            p.setId(1L);
            return p;
        });

        final Pedido pedidoSalvo = this.pedidoService.salvar(pedido);
        Assertions.assertEquals(1, pedidoSalvo.getItens().size());
        Assertions.assertEquals(BigDecimal.valueOf(20), pedidoSalvo.getTotal());
        Assertions.assertNotNull(pedidoSalvo.getId());
    }

    private Cliente criaCliente() {
        final Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("João");
        cliente.setDocumento("958.518.560-17");
        return cliente;
    }

    private Pedido criaPedido(final Cliente cliente) {
        final Pedido pedido = new Pedido();
        pedido.setCliente(cliente);

        final ItemPedido itemPedido = new ItemPedido();
        itemPedido.setValor(BigDecimal.TEN);
        itemPedido.setQuantidade(2);
        itemPedido.setDescricao("Produto X");
        pedido.setItens(Collections.singletonList(itemPedido));
        return pedido;
    }

}