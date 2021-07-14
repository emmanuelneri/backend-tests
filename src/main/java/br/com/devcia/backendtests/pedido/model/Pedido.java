package br.com.devcia.backendtests.pedido.model;

import br.com.devcia.backendtests.cliente.model.Cliente;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Cliente não deve ser nulo")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull(message = "O pedido deve conter pelo menos um item")
    @Size(min =  1)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id", nullable = false)
    private List<ItemPedido> itens = new ArrayList<>();

    @NotNull(message = "Total não deve ser nulo")
    private BigDecimal total = BigDecimal.ZERO;

    @NotNull(message = "Status não deve ser nulo")
    private Status status = Status.ABERTO;

    public void calculaTotal() {
        this.itens.forEach(item -> {
            item.calculaTotal();
            this.total = this.total.add(item.getTotal());
        });

    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ItemPedido> getItens() {
        return new ArrayList<>(itens);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
