package br.com.devcia.backendtests.pedido.controller;

import br.com.devcia.backendtests.pedido.model.Pedido;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@JsonSerialize
record CriarPedidoDTO(List<ItemPedidoDTO> itens) {

    @Mapper
    public interface CriarPedidoMapper {

        CriarPedidoMapper INSTANCE = Mappers.getMapper(CriarPedidoMapper.class);

        @Mapping(target = "itens", source = "itens")
        Pedido criarPedidoDTOParaPedido(CriarPedidoDTO dto);
    }

    static Pedido converter(final CriarPedidoDTO dto) {
        return CriarPedidoMapper.INSTANCE.criarPedidoDTOParaPedido(dto);
    }

}





