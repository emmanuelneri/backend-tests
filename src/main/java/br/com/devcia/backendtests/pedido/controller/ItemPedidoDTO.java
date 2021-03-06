package br.com.devcia.backendtests.pedido.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;

@JsonSerialize
record ItemPedidoDTO(Long id, String descricao, BigDecimal valor, int quantidade, BigDecimal total) {
}
