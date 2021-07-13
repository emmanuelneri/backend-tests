package br.com.devcia.backendtests.pedido.service;

import br.com.devcia.backendtests.cliente.model.Cliente;
import br.com.devcia.backendtests.cliente.service.ClienteService;
import br.com.devcia.backendtests.pedido.model.Pedido;
import br.com.devcia.backendtests.pedido.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;

    public PedidoService(final PedidoRepository pedidoRepository,
                         final ClienteService clienteService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
    }

    @Transactional
    public Pedido salvar(@Valid final Pedido pedido) {
        final Cliente cliente = this.clienteService.criarClienteSeNaoExistir(pedido.getCliente());
        pedido.setCliente(cliente);
        pedido.calculaTotal();
        this.pedidoRepository.save(pedido);
        return pedido;
    }

}
