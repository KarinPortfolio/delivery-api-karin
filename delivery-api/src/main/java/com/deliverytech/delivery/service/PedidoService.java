package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.enums.StatusPedido;
import java.util.List;
import java.util.Optional;

public interface PedidoService {
    Pedido criarPedido(Long clienteId, Long restauranteId);
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