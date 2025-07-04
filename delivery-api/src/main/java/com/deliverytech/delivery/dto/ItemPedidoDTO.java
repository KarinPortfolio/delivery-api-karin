package com.deliverytech.delivery.dto;

import java.math.BigDecimal;

import lombok.Data; 
import jakarta.validation.constraints.Min; 
import jakarta.validation.constraints.NotNull; 

@Data 
public class ItemPedidoDTO {

    @NotNull(message = "O ID do produto não pode ser nulo")
    private Long produtoId; // ID do produto que está sendo pedido

    @Min(value = 1, message = "A quantidade deve ser de pelo menos 1")
    private Integer quantidade; // Quantidade deste produto no pedido
    private String nomeProduto; 
    private BigDecimal precoUnitario; 
    private BigDecimal subtotal; 

    
}