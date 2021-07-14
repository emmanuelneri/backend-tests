package br.com.devcia.backendtests.cliente.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record ClienteDTO(Long id, String nome, String documento) {
}
