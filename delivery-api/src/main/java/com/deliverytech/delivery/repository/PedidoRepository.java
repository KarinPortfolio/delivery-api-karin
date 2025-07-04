package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.entity.Cliente; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Método para buscar pedidos por cliente, ordenados pela data do pedido
    List<Pedido> findByClienteOrderByDataPedidoDesc(Cliente cliente);

    // Método para buscar um pedido pelo número (assumindo que o número é único)
    Pedido findByNumeroPedido(String numeroPedido);
   
    // Listar os pedidos por intervalo de data
    List<Pedido> findByDataPedidoBetweenOrderByDataPedidoDesc(LocalDateTime inicio, LocalDateTime fim);

}