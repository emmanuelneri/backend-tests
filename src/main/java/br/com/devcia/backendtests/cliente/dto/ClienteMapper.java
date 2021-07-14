package br.com.devcia.backendtests.cliente.dto;

import br.com.devcia.backendtests.cliente.model.Cliente;
import br.com.devcia.backendtests.pedido.controller.PedidoMapper;

public final class ClienteMapper {

    public static final ClienteMapper INSTANCE = new ClienteMapper();

    private ClienteMapper() {
    }

    public Cliente clienteDTOParaCliente(final ClienteDTO dto) {
        if (dto == null) {
            return null;
        }

        final Cliente cliente = new Cliente();
        cliente.setId(dto.id());
        cliente.setNome(dto.nome());
        cliente.setDocumento(dto.documento());
        return cliente;
    }

    public ClienteDTO clienteParaClienteDTO(final Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getDocumento());
    }
}
