package br.com.devcia.backendtests.pedido.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

@JsonSerialize
record CriacaoItemPedidoDTO(String descricao, BigDecimal valor, int quantidade) {
}
