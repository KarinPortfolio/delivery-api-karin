// PedidoResponseDTO.java
package com.deliverytech.delivery.dto;

import com.deliverytech.delivery.enums.StatusPedido;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoResponseDTO {
    private Long id;
    private String numeroPedido;
    private Long clienteId; // Ou ClienteResponseDTO
    private Long restauranteId; // Ou RestauranteResponseDTO
    private LocalDateTime dataCriacao;
    private StatusPedido status;
    private BigDecimal valorTotal;
    private List<ItemPedidoResponseDTO> itens; // Lista de DTOs de itens

    // Adicione um construtor ou método estático 'fromEntity' para mapear de Pedido para PedidoResponseDTO
    // Ex: public static PedidoResponseDTO fromEntity(Pedido pedido) { ... }
}

