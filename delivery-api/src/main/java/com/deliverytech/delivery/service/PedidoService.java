package com.deliverytech.delivery.service;

import java.util.List;
import java.util.Optional;

import com.deliverytech.delivery.dto.ItemPedidoDTO;
import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.enums.StatusPedido;

public interface PedidoService {
    Pedido criarPedido(Long clienteId, Long restauranteId);
    // Novo método para criar pedido com itens e endereço de entrega
    Pedido criarPedidoComItens(Long clienteId, Long restauranteId, String enderecoEntrega, List<ItemPedidoDTO> itens);
    Pedido adicionarItem(Long pedidoId, Long produtoId, Integer quantidade);
    Pedido confirmarPedido(Long pedidoId);
    Optional<Pedido> buscarPorId(Long id);
    List<Pedido> listarPorCliente(Long clienteId);
    Optional<Pedido> buscarPorNumero(String numeroPedido);
    Pedido atualizarStatus(Long pedidoId, StatusPedido status);
    Pedido cancelarPedido(Long pedidoId, String motivo);

    List<Pedido> listarTodosPedidos();
    List<Pedido> listarPedidosDoDia();
}