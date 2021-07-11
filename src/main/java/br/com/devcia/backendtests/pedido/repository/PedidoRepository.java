package br.com.devcia.backendtests.pedido.repository;

import br.com.devcia.backendtests.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
