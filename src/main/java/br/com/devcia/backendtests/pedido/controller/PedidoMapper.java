package br.com.devcia.backendtests.pedido.controller;

import br.com.devcia.backendtests.cliente.dto.ClienteDTO;
import br.com.devcia.backendtests.cliente.dto.ClienteMapper;
import br.com.devcia.backendtests.pedido.model.ItemPedido;
import br.com.devcia.backendtests.pedido.model.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public final class PedidoMapper {

    public static final PedidoMapper INSTANCE = new PedidoMapper();

    private PedidoMapper() {
    }

    public PedidoDTO pedidoParaPedidoDTO(final Pedido pedido) {
        final ClienteDTO cliente = ClienteMapper.INSTANCE.clienteParaClienteDTO(pedido.getCliente());
        final List<ItemPedidoDTO> itens = itemPedidoParaItemPedidoDTO(pedido.getItens());
        return new PedidoDTO(pedido.getId(), cliente, itens, pedido.getTotal(), pedido.getStatus());
    }

    public Pedido criacaoPedidoDTOParaPedido(final CriacaoPedidoDTO dto) {
        if (dto == null) {
            return null;
        }

        final Pedido pedido = new Pedido();
        pedido.setCliente(ClienteMapper.INSTANCE.clienteDTOParaCliente(dto.cliente()));
        pedido.setItens(itemPedidoDTOParaItemPedido(dto.itens()));


        return pedido;
    }

    private List<ItemPedido> itemPedidoDTOParaItemPedido(final List<CriacaoItemPedidoDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(dto -> {
                    final ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setDescricao(dto.descricao());
                    itemPedido.setQuantidade(dto.quantidade());
                    itemPedido.setValor(dto.valor());

                    return itemPedido;
                }).collect(Collectors.toList());
    }

    private List<ItemPedidoDTO> itemPedidoParaItemPedidoDTO(final List<ItemPedido> itens) {
        if (itens == null) {
            return null;
        }
        return itens.stream()
                .map(item -> new ItemPedidoDTO(item.getId(), item.getDescricao(),
                        item.getValor(), item.getQuantidade(), item.getTotal()))
                .collect(Collectors.toList());
    }

}
