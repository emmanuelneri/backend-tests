package br.com.devcia.backendtests.pedido.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id", nullable = false)
    private List<ItemPedido> itens = new ArrayList<>();

    private BigDecimal total = BigDecimal.ZERO;

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
}
