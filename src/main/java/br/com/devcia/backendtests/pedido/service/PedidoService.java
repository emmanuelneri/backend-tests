package br.com.devcia.backendtests.pedido.service;

import br.com.devcia.backendtests.pedido.model.Pedido;
import br.com.devcia.backendtests.pedido.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(final PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Pedido salvar(final Pedido pedido) {
        pedido.calculaTotal();
        pedidoRepository.save(pedido);
        return pedido;
    }

}
