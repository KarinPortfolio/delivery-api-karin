package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    /**
     * Encontra e retorna uma lista de todos os itens de pedido
     * associados a um determinado ID de pedido.
     *
     * @param pedidoId O ID do pedido ao qual os itens pertencem.
     * @return Uma lista de ItemPedido associados ao pedido.
     */
    List<ItemPedido> findByPedidoId(Long pedidoId);
}