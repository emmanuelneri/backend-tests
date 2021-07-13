package br.com.devcia.backendtests.cliente.service;

import br.com.devcia.backendtests.cliente.exception.ClienteNaoEncontradoException;
import br.com.devcia.backendtests.cliente.model.Cliente;
import br.com.devcia.backendtests.cliente.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(final ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente salvar(@Valid final Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public Optional<Cliente> buscarPorId(final Long id) {
        return this.clienteRepository.findById(id);
    }

    public Cliente criarClienteSeNaoExistir(final Cliente cliente) {
        if (cliente.getId() == null) {
            return salvar(cliente);
        }

        return buscarPorId(cliente.getId())
                .orElseThrow(() -> new ClienteNaoEncontradoException("id não encontrado: " + cliente.getId()));
    }

}
