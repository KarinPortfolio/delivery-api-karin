package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ItemPedidoDTO;
import com.deliverytech.delivery.entity.ItemPedido;
import com.deliverytech.delivery.entity.Produto; // Necessário para associar o item a um produto
import com.deliverytech.delivery.repository.ItemPedidoRepository;
import com.deliverytech.delivery.repository.ProdutoRepository; // Assumindo que você tem um ProdutoRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Para controle transacional

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository; // Para buscar informações do produto

  //  @Autowired
   //private PedidoRepository pedidoRepository; // Para buscar informações do pedido (se necessário)

    /**
     * Cria um novo item de pedido.
     * Este método é mais genérico e pode ser usado se o item não for associado
     * a um pedido existente no momento da criação inicial.
     * Para adicionar um item a um pedido já existente, use o método adicionarItem
     * no PedidoService (como você já tem).
     *
     * @param itemPedidoDTO Dados do item de pedido a ser criado.
     * @return O ItemPedido criado e salvo.
     * @throws IllegalArgumentException Se o produto não for encontrado.
     */
    @Transactional
    public ItemPedido criarItemPedido(ItemPedidoDTO itemPedidoDTO) {
        Produto produto = produtoRepository.findById(itemPedidoDTO.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + itemPedidoDTO.getProdutoId()));

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
        itemPedido.setPrecoUnitario(itemPedidoDTO.getPrecoUnitario());
        // O campo 'pedido' não é setado aqui, pois este método cria um item "órfão" inicialmente.
        // Se a intenção é que um item sempre pertença a um pedido, este método deveria receber um pedidoId.
        // O método 'adicionarItem' no PedidoService é o local mais adequado para criar e associar um ItemPedido a um Pedido.
        return itemPedidoRepository.save(itemPedido);
    }

    /**
     * Busca um item de pedido pelo seu ID.
     *
     * @param id O ID do item de pedido.
     * @return Um Optional contendo o ItemPedido, se encontrado.
     */
    public Optional<ItemPedido> buscarItemPedidoPorId(Long id) {
        return itemPedidoRepository.findById(id);
    }

    /**
     * Atualiza um item de pedido existente.
     *
     * @param id O ID do item de pedido a ser atualizado.
     * @param itemPedidoDTO Os novos dados para o item de pedido.
     * @return O ItemPedido atualizado.
     * @throws IllegalArgumentException Se o item de pedido ou o produto não forem encontrados.
     */
    @Transactional
    public ItemPedido atualizarItemPedido(Long id, ItemPedidoDTO itemPedidoDTO) {
        ItemPedido itemExistente = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item de pedido não encontrado com ID: " + id));

        // Busca o produto, garantindo que ele exista
        Produto produto = produtoRepository.findById(itemPedidoDTO.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com ID: " + itemPedidoDTO.getProdutoId()));

        // Atualiza os campos do item existente
        itemExistente.setProduto(produto);
        itemExistente.setQuantidade(itemPedidoDTO.getQuantidade());
        itemExistente.setPrecoUnitario(itemPedidoDTO.getPrecoUnitario());

        // Sugestão: Adicione lógica de validação aqui, por exemplo,
        // para impedir a atualização de itens de pedidos já finalizados.
        // if (itemExistente.getPedido() != null && itemExistente.getPedido().getStatus() != StatusPedido.PENDENTE) {
        //     throw new IllegalStateException("Não é possível atualizar item de pedido com status " + itemExistente.getPedido().getStatus());
        // }

        return itemPedidoRepository.save(itemExistente);
    }

    /**
     * Deleta um item de pedido pelo seu ID.
     *
     * @param id O ID do item de pedido a ser deletado.
     * @throws IllegalArgumentException Se o item de pedido não for encontrado.
     */
    @Transactional
    public void deletarItemPedido(Long id) {
        if (!itemPedidoRepository.existsById(id)) {
            throw new IllegalArgumentException("Item de pedido não encontrado com ID: " + id);
        }
        itemPedidoRepository.deleteById(id);
    }

    /**
     * Lista todos os itens de pedido existentes.
     *
     * @return Uma lista de todos os ItemPedido.
     */
    public List<ItemPedido> listarTodosItensPedido() {
        return itemPedidoRepository.findAll();
    }

    /**
     * Lista todos os itens de pedido associados a um pedido específico.
     *
     * @param pedidoId O ID do pedido.
     * @return Uma lista de ItemPedido pertencentes ao pedido.
     * @throws IllegalArgumentException Se o pedido não for encontrado.
     */
    public List<ItemPedido> listarItensPorPedido(Long pedidoId) {
        // Verifica se o pedido existe antes de tentar buscar seus itens
       // Pedido pedido = pedidoRepository.findById(pedidoId)
        //        .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + pedidoId));

        // Assume que você tem um método findByPedidoId no seu ItemPedidoRepository
        return itemPedidoRepository.findByPedidoId(pedidoId);
    }
}