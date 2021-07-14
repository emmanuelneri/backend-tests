package br.com.devcia.backendtests.pedido.controller;

import br.com.devcia.backendtests.cliente.dto.ClienteDTO;
import br.com.devcia.backendtests.pedido.model.Pedido;
import br.com.devcia.backendtests.pedido.model.Status;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.util.List;

@JsonSerialize
record PedidoDTO(Long id, ClienteDTO cliente, List<ItemPedidoDTO> itens, BigDecimal total, Status status) {

    static PedidoDTO converter(final Pedido pedido) {
        return PedidoMapper.INSTANCE.pedidoParaPedidoDTO(pedido);
    }

}





