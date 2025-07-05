package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.dto.ItemPedidoDTO;
import com.deliverytech.delivery.dto.ItemPedidoResponseDTO;
import com.deliverytech.delivery.entity.ItemPedido;
import com.deliverytech.delivery.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/itens-pedido") // é uma referência para pedido
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    private ItemPedidoResponseDTO toResponseDTO(ItemPedido itemPedido) {
        if (itemPedido == null) {
            return null;
        }
        ItemPedidoResponseDTO dto = new ItemPedidoResponseDTO();
        dto.setId(itemPedido.getId());
        dto.setProdutoId(itemPedido.getProduto() != null ? itemPedido.getProduto().getId() : null);
        dto.setQuantidade(itemPedido.getQuantidade());
        dto.setPrecoUnitario(itemPedido.getPrecoUnitario());
        dto.setSubtotal(itemPedido.getPrecoTotal()); 
        return dto;
    }

    // --- Endpoints CRUD para ItemPedido ---

    @PostMapping
    public ResponseEntity<?> criarItemPedido(@RequestBody @Valid ItemPedidoDTO itemPedidoDTO) {
        try {
            ItemPedido novoItemPedido = itemPedidoService.criarItemPedido(itemPedidoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(novoItemPedido));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao criar item de pedido: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro interno ao criar o item de pedido. Tente novamente mais tarde.");
        }
    }

    // Buscar item de pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarItemPedidoPorId(@PathVariable Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoService.buscarItemPedidoPorId(id);
        if (itemPedido.isPresent()) {
            return ResponseEntity.ok(toResponseDTO(itemPedido.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar um item de pedido existente    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarItemPedido(@PathVariable Long id, @RequestBody @Valid ItemPedidoDTO itemPedidoDTO) {
        try {
            ItemPedido itemAtualizado = itemPedidoService.atualizarItemPedido(id, itemPedidoDTO);
            return ResponseEntity.ok(toResponseDTO(itemAtualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar item de pedido: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro interno ao atualizar o item de pedido. Tente novamente mais tarde.");
        }
    }

    // Deletar um item de pedido por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarItemPedido(@PathVariable Long id) {
        try {
            itemPedidoService.deletarItemPedido(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content para deleção bem-sucedida
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item de pedido não encontrado: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro interno ao deletar o item de pedido. Tente novamente mais tarde.");
        }
    }

    // Listar todos os itens de pedido (pode ser útil para fins administrativos/debugging)
    @GetMapping
    public ResponseEntity<List<ItemPedidoResponseDTO>> listarTodosItensPedido() {
        List<ItemPedido> itens = itemPedidoService.listarTodosItensPedido();
        List<ItemPedidoResponseDTO> dtos = itens.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Listar itens de pedido por ID de Pedido (se não houver um endpoint similar no PedidoController)
    @GetMapping("/por-pedido/{pedidoId}")
    public ResponseEntity<List<ItemPedidoResponseDTO>> listarItensPorPedido(@PathVariable Long pedidoId) {
        List<ItemPedido> itens = itemPedidoService.listarItensPorPedido(pedidoId);
        List<ItemPedidoResponseDTO> dtos = itens.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}