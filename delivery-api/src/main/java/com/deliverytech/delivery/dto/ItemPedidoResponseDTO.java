package com.deliverytech.delivery.dto;

import java.math.BigDecimal; 

import lombok.Data; 

@Data
public class ItemPedidoResponseDTO {

    private Long id; // ID do item do pedido 
    private Long produtoId; // ID do produto associado a este item
    private String nomeProduto; // Nome do produto (para exibição)
    private String descricaoProduto; // Descrição curta do produto 
    private BigDecimal precoUnitario; // Preço unitário do produto no momento do pedido
    private Integer quantidade; // Quantidade deste produto no pedido
    private BigDecimal subtotal; // Subtotal calculado para este item (quantidade * precoUnitario)

   
}