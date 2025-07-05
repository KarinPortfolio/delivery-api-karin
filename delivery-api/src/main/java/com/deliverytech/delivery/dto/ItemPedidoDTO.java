package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
@Data
public class ItemPedidoDTO {
    @NotNull(message = "O ID do produto é obrigatório")
    private Long produtoId;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    private Integer quantidade;

    @NotNull(message = "O preço unitário é obrigatório")
    @Min(value = 0, message = "O preço unitário deve ser no mínimo 0")
    private BigDecimal precoUnitario;

    public BigDecimal getSubtotal() {
        return this.precoUnitario.multiply(new BigDecimal(this.quantidade));
    }
}