package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.NotNull; // Para validação


public class PedidoCreationDTO {

    @NotNull(message = "O ID do cliente é obrigatório.")
    private Long clienteId;

    @NotNull(message = "O ID do restaurante é obrigatório.")
    private Long restauranteId;

    // Opcional: Se você quiser criar um pedido já com itens
    // private List<ItemPedidoDTO> itens;

    public PedidoCreationDTO() {
    }

    public PedidoCreationDTO(Long clienteId, Long restauranteId) {
        this.clienteId = clienteId;
        this.restauranteId = restauranteId;
    }

    // Opcional: Construtor com itens
    // public PedidoCreationDTO(Long clienteId, Long restauranteId, List<ItemPedidoDTO> itens) {
    //     this.clienteId = clienteId;
    //     this.restauranteId = restauranteId;
    //     this.itens = itens;
    // }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    // Opcional: Getters e Setters para itens
    // public List<ItemPedidoDTO> getItens() {
    //     return itens;
    // }

    // public void setItens(List<ItemPedidoDTO> itens) {
    //     this.itens = itens;
    // }

}
 