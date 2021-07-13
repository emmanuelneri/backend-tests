package br.com.devcia.backendtests.cliente.repository;

import br.com.devcia.backendtests.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
