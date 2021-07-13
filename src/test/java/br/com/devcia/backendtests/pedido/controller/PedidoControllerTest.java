package br.com.devcia.backendtests.pedido.controller;

import br.com.devcia.backendtests.cliente.model.Cliente;
import br.com.devcia.backendtests.pedido.model.ItemPedido;
import br.com.devcia.backendtests.pedido.model.Pedido;
import br.com.devcia.backendtests.pedido.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService service;

    @Test
    public void deveReceberSolicitacaoParaRealizarPedido() throws Exception {
        final Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente");
        cliente.setDocumento("373.115.180-40");

        final ItemPedido itemPedido = new ItemPedido();
        itemPedido.setValor(BigDecimal.TEN);
        itemPedido.setQuantidade(2);
        itemPedido.setDescricao("Produto X");

        final Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setCliente(cliente);
        pedido.setItens(Collections.singletonList(itemPedido));
        pedido.calculaTotal();

        Mockito.when(service.salvar(Mockito.any(Pedido.class))).thenReturn(pedido);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"cliente\":{\"id\":1,\"nome\":\"Cliente\",\"documento\":\"373.115.180-40\"},\"itens\":[{\"id\":null,\"descricao\":\"Produto X\",\"valor\":10,\"quantidade\":2,\"total\":20}],\"total\":20,\"status\":\"ABERTO\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"cliente\":{\"id\":1,\"nome\":\"Cliente\",\"documento\":\"373.115.180-40\"},\"itens\":[{\"id\":null,\"descricao\":\"Produto X\",\"valor\":10,\"quantidade\":2,\"total\":20}],\"total\":20,\"status\":\"ABERTO\"}"));
    }

}