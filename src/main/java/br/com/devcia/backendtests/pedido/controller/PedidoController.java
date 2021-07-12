package br.com.devcia.backendtests.pedido.controller;

import br.com.devcia.backendtests.pedido.model.Pedido;
import br.com.devcia.backendtests.pedido.service.PedidoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(final PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public Pedido salvar(@RequestBody final CriarPedidoDTO pedido) {
        return this.pedidoService.salvar(CriarPedidoDTO.converter(pedido));
    }

}
