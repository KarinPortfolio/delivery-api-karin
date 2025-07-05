package com.deliverytech.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.deliverytech.delivery.dto.ItemPedidoResponseDTO;
import com.deliverytech.delivery.dto.PedidoResponseDTO;
import com.deliverytech.delivery.entity.ItemPedido;
import com.deliverytech.delivery.entity.Pedido;

@Component
public class PedidoMapper {

    public PedidoResponseDTO toResponseDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(pedido.getId());
        dto.setNumeroPedido(pedido.getNumeroPedido());
        dto.setClienteId(pedido.getCliente() != null ? pedido.getCliente().getId() : null);
        dto.setRestauranteId(pedido.getRestaurante() != null ? pedido.getRestaurante().getId() : null);
        dto.setDataCriacao(pedido.getDataPedido());
        dto.setStatus(pedido.getStatus());
        dto.setValorTotal(pedido.getValorTotal());

        if (pedido.getItens() != null) {
            dto.setItens(pedido.getItens().stream()
                    .map(this::toItemPedidoResponseDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private ItemPedidoResponseDTO toItemPedidoResponseDTO(ItemPedido itemPedido) {
        if (itemPedido == null) {
            return null;
        }
        ItemPedidoResponseDTO dto = new ItemPedidoResponseDTO();
        dto.setId(itemPedido.getId());
        dto.setProdutoId(itemPedido.getProduto() != null ? itemPedido.getProduto().getId() : null);
        dto.setNomeProduto(itemPedido.getProduto() != null ? itemPedido.getProduto().getNome() : null);
        dto.setQuantidade(itemPedido.getQuantidade());
        dto.setPrecoUnitario(itemPedido.getPrecoUnitario());
        dto.setSubtotal(itemPedido.getQuantidade() != null && itemPedido.getPrecoUnitario() != null
            ? itemPedido.getPrecoUnitario().multiply(new java.math.BigDecimal(itemPedido.getQuantidade()))
            : null);
        return dto;
    }
}