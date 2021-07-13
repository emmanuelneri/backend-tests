package br.com.devcia.backendtests.cliente.service;

import br.com.devcia.backendtests.cliente.exception.ClienteNaoEncontradoException;
import br.com.devcia.backendtests.cliente.model.Cliente;
import br.com.devcia.backendtests.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void deveCriarUmClienteCasoIdForNulo() {
        final Long idGerado = 1L;
        final Cliente cliente = new Cliente();

        Mockito.when(clienteRepository.save(cliente)).then(invocationOnMock -> {
            final Cliente cli = invocationOnMock.getArgument(0);
            cli.setId(idGerado);
            return cli;
        });

        final Cliente clienteRetornado = clienteService.criarClienteSeNaoExistir(cliente);
        Assertions.assertEquals(idGerado, clienteRetornado.getId());
    }

    @Test
    public void deveRetornarUmClienteQuandoEleExistir() {
        final Cliente cliente = new Cliente();
        cliente.setId(10L);

        Mockito.when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.of(cliente));

        final Cliente clienteRetornado = clienteService.criarClienteSeNaoExistir(cliente);
        Assertions.assertEquals(cliente.getId(), clienteRetornado.getId());

        Mockito.verify(clienteRepository, Mockito.times(0)).save(Mockito.any(Cliente.class));
    }

    @Test
    public void deveLancarErroAoConsultarUmClienteQueNaoExiste() {
        final Cliente cliente = new Cliente();
        cliente.setId(10L);

        Assertions.assertThrows(ClienteNaoEncontradoException.class, () -> {
            Mockito.when(clienteRepository.findById(cliente.getId())).thenReturn(Optional.empty());
            clienteService.criarClienteSeNaoExistir(cliente);
        });

        Mockito.verify(clienteRepository, Mockito.times(0)).save(Mockito.any(Cliente.class));
    }

}