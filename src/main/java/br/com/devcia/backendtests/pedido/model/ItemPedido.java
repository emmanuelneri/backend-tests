package br.com.devcia.backendtests.pedido.model;

import br.com.devcia.backendtests.pedido.exception.QuantidadeInvalidaException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal valor = BigDecimal.ZERO;

    @NotNull
    private int quantidade;

    @NotNull
    private BigDecimal total = BigDecimal.ZERO;

    public ItemPedido() {
    }

    ItemPedido(BigDecimal valor, int quantidade) {
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            throw QuantidadeInvalidaException.quantidadeNegativaException();
        }

        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    void calculaTotal() {
        this.total = this.valor.multiply(BigDecimal.valueOf(this.quantidade));
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "valor=" + valor +
                ", quantidade=" + quantidade +
                '}';
    }
}
