
package com.deliverytech.delivery.entity;

import com.deliverytech.delivery.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataPedido;
    private String enderecoEntrega;
    private BigDecimal subtotal;
    private BigDecimal taxaEntrega;
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    // Adiciona um item ao pedido
    public void adicionarItem(ItemPedido item) {
        if (this.getItens() == null) {
            this.setItens(new java.util.ArrayList<>());
        }
        this.getItens().add(item);
        item.setPedido(this);
    }
        public void confirmar() {
        this.setStatus(com.deliverytech.delivery.enums.StatusPedido.CONFIRMADO);
    
    }

        private String observacoes;

    public String getObservacoes() {
        return observacoes == null ? "" : observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    private String numeroPedido;
}

 