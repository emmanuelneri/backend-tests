package br.com.devcia.backendtests.pedido.service;

import br.com.devcia.backendtests.pedido.model.ItemPedido;
import br.com.devcia.backendtests.pedido.model.Pedido;
import br.com.devcia.backendtests.pedido.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
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

    @InjectMocks
    private PedidoService pedidoService;

    @RepeatedTest(5)
    @DisplayName("Deve salvar um novo pedido, calculando valor total dos itens e do pedido")
    public void deveSalvarUmNovoPedido() {
        final Pedido pedido = new Pedido();
        final ItemPedido itemPedido = new ItemPedido();
        itemPedido.setValor(BigDecimal.TEN);
        itemPedido.setQuantidade(2);
        pedido.setItens(Collections.singletonList(itemPedido));

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

}