package com.deliverytech.delivery.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemPedidoResponseDTO {
    private Long id;
    private Long produtoId; // Ou ProdutoResponseDTO
    private String nomeProduto; // Adicione este campo se quiser o nome do produto na resposta
    private Integer quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;
}