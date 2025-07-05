package com.deliverytech.delivery.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data; // Adicionado para gerar getters/setters
import java.util.List;

@Data // Adicionado para gerar getters/setters automaticamente
public class PedidoDTO {

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "O ID do restaurante é obrigatório")
    private Long restauranteId;

    @NotBlank(message = "O endereço de entrega não pode estar em branco")
    private String enderecoEntrega;

    @Valid // Valida cada ItemPedidoDTO na lista
    @NotEmpty(message = "Pelo menos um item deve ser adicionado ao pedido")
    private List<ItemPedidoDTO> itens;
    
    // Importante: para que Lombok @Data funcione, certifique-se que lombok está na dependência do seu pom.xml
}