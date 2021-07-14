package br.com.devcia.backendtests.pedido.controller;

import br.com.devcia.backendtests.PostgresContainerInitializer;
import br.com.devcia.backendtests.cliente.dto.ClienteDTO;
import br.com.devcia.backendtests.pedido.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {PostgresContainerInitializer.class})
public class PedidoControllerIT {

    @LocalServerPort
    int port;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    RestTemplate restTemplate;

    HttpHeaders headers;

    @BeforeEach
    public void before() {
        this.headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        this.restTemplate = this.restTemplateBuilder
                .rootUri(String.format("http://localhost:%d", port))
                .build();
    }

    @Test
    @DisplayName("deve salvar um novo pedido e um novo cliente quando requisitado a API /pedidos")
    public void deveSalvarUmNovoPedido() throws IOException {
        final String corpoRequisicao = new String(Objects.requireNonNull(getClass().getClassLoader()
                .getResourceAsStream("pedidos/criacao-pedido.json"))
                .readAllBytes());

        final ResponseEntity<PedidoDTO> resposta = this.restTemplate
                .postForEntity("/pedidos", new HttpEntity<>(corpoRequisicao, this.headers), PedidoDTO.class);

        Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());

        final PedidoDTO pedido = resposta.getBody();
        Assertions.assertNotNull(pedido);
        Assertions.assertNotNull(pedido.id());
        Assertions.assertEquals(Status.ABERTO, pedido.status());
        Assertions.assertEquals(BigDecimal.valueOf(20), pedido.total());

        final ClienteDTO cliente = pedido.cliente();
        Assertions.assertNotNull(cliente);
        Assertions.assertNotNull(cliente.id());
        Assertions.assertEquals("Cliente", cliente.nome());
        Assertions.assertEquals("373.115.180-40", cliente.documento());

        final List<ItemPedidoDTO> itens = pedido.itens();
        Assertions.assertNotNull(itens);
        Assertions.assertEquals(1, itens.size());

        final ItemPedidoDTO item = itens.stream().findAny().orElseThrow(RuntimeException::new);
        Assertions.assertNotNull(item.id());
        Assertions.assertEquals("Produto X", item.descricao());
        Assertions.assertEquals(BigDecimal.TEN, item.valor());
        Assertions.assertEquals(2, item.quantidade());
        Assertions.assertEquals(BigDecimal.valueOf(20), item.total());
    }

    @Test
    @DisplayName("deve retornar requisição invalida (HTTP 400) quando enviar um pedido sem cliente")
    public void deveRetornarRequisicaoInvalidaQuandoEnviarUmPedidoSemCliente() {
        final CriacaoItemPedidoDTO itemPedido = new CriacaoItemPedidoDTO("Produto", BigDecimal.ONE, 1);
        final CriacaoPedidoDTO pedido = new CriacaoPedidoDTO(null, Collections.singletonList(itemPedido));

        final HttpClientErrorException.BadRequest exception = Assertions.assertThrows(HttpClientErrorException.BadRequest.class,
                () -> this.restTemplate.postForEntity("/pedidos", new HttpEntity<>(pedido, this.headers), Object.class));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("400 : [{\"mensagem\":\"salvar.pedido.cliente: Cliente não deve ser nulo\"}]", exception.getMessage());
    }

    @Test
    @DisplayName("deve retornar requisição invalida (HTTP 400) quando enviar um pedido sem item")
    public void deveRetornarRequisicaoInvalidaQuandoEnviarUmPedidoSemItem() {
        final ClienteDTO cliente = new ClienteDTO(null, "Cliente", "373.115.180-40");
        final CriacaoPedidoDTO pedido = new CriacaoPedidoDTO(cliente, null);

        final HttpClientErrorException.BadRequest exception = Assertions.assertThrows(HttpClientErrorException.BadRequest.class,
                () -> this.restTemplate.postForEntity("/pedidos", new HttpEntity<>(pedido, this.headers), Object.class));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        Assertions.assertEquals("400 : [{\"mensagem\":\"salvar.pedido.itens: O pedido deve conter pelo menos um item\"}]", exception.getMessage());
    }

}
