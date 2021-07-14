package br.com.devcia.backendtests.pedido.controller;

import br.com.devcia.backendtests.cliente.dto.ClienteDTO;
import br.com.devcia.backendtests.pedido.model.Pedido;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize
record CriacaoPedidoDTO(ClienteDTO cliente, List<CriacaoItemPedidoDTO> itens) {

    static Pedido converter(final CriacaoPedidoDTO dto) {
        return PedidoMapper.INSTANCE.criacaoPedidoDTOParaPedido(dto);
    }

}





