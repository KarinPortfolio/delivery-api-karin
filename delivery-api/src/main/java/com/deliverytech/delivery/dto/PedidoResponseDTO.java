package com.deliverytech.delivery.dto;

import com.deliverytech.delivery.enums.StatusPedido; 
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data; 

@Data 
public class PedidoResponseDTO {

    private Long id; 
    private ClienteResponseDTO cliente;
    private RestauranteResponseDTO restaurante;
    private String enderecoEntrega; 
    private StatusPedido status; 
    private LocalDateTime dataHora; 
    private BigDecimal subtotal; 
    private BigDecimal taxaEntrega; 
    private BigDecimal total; 
    private List<ItemPedidoResponseDTO> itens;
}