package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.dto.ItemPedidoDTO; // Importe o DTO para adicionar itens
import com.deliverytech.delivery.dto.PedidoCreationDTO; // Importe o DTO para criar pedidos
import com.deliverytech.delivery.entity.Pedido;
import com.deliverytech.delivery.enums.StatusPedido;
import com.deliverytech.delivery.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // Para validação do DTO

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Sugestão: Adicione um mapper se for usar DTOs de resposta para conversão de Pedido para PedidoResponseDTO
    // @Autowired
    // private PedidoMapper pedidoMapper; // Você precisaria criar esta classe/interface



    // Criar novo pedido usando PedidoCreationDTO no corpo da requisição
    @PostMapping
    public ResponseEntity<?> criarPedido(@RequestBody @Valid PedidoCreationDTO pedidoDTO) {
        try {
            Pedido pedidoCriado = pedidoService.criarPedido(pedidoDTO.getClienteId(), pedidoDTO.getRestauranteId());
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao criar pedido: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro interno ao criar o pedido. Tente novamente mais tarde.");
        }
    }
    // Adicionar item ao pedido usando ItemPedidoDTO no corpo da requisição
    @PostMapping("/{pedidoId}/itens") // Ou o caminho que você definiu para adicionar itens
public ResponseEntity<?> adicionarItem(@PathVariable Long pedidoId, @RequestBody @Valid ItemPedidoDTO itemPedidoDTO) {
        try {
            Pedido pedidoAtualizado = pedidoService.adicionarItem(pedidoId, itemPedidoDTO.getProdutoId(), itemPedidoDTO.getQuantidade());
            
            // Sugestão: Retorne um PedidoResponseDTO
            // return ResponseEntity.ok(pedidoMapper.toResponseDTO(pedidoAtualizado));
            return ResponseEntity.ok(pedidoAtualizado); // Por enquanto, retorna a entidade direta
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao adicionar item: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro interno ao adicionar o item. Tente novamente mais tarde.");
        }
    }



    // Confirmar pedido
    @PutMapping("/{pedidoId}/confirmar")
    public ResponseEntity<?> confirmarPedido(@PathVariable Long pedidoId) {
        try {
            Pedido pedidoConfirmado = pedidoService.confirmarPedido(pedidoId);
            // return ResponseEntity.ok(pedidoMapper.toResponseDTO(pedidoConfirmado));
            return ResponseEntity.ok(pedidoConfirmado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao confirmar pedido: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro interno ao confirmar o pedido. Tente novamente mais tarde.");
        }
    }

  

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscarPorId(id);

        if (pedido.isPresent()) {
            // return ResponseEntity.ok(pedidoMapper.toResponseDTO(pedido.get()));
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    // Listar pedidos por cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> listarPorCliente(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.listarPorCliente(clienteId);
        
        // Sugestão: Mapear a lista de Pedido para uma lista de PedidoResponseDTO
        // List<PedidoResponseDTO> dtoList = pedidos.stream()
        //                                       .map(pedidoMapper::toResponseDTO)
        //                                       .collect(Collectors.toList());
        // return ResponseEntity.ok(dtoList);
        return ResponseEntity.ok(pedidos);
    }



    // Buscar pedido por número
    @GetMapping("/numero/{numeroPedido}")
    public ResponseEntity<?> buscarPorNumero(@PathVariable String numeroPedido) {
        Optional<Pedido> pedido = pedidoService.buscarPorNumero(numeroPedido);

        if (pedido.isPresent()) {
            // return ResponseEntity.ok(pedidoMapper.toResponseDTO(pedido.get()));
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    // Atualizar status do pedido
    @PutMapping("/{pedidoId}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long pedidoId,
                                            @RequestParam StatusPedido status) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizarStatus(pedidoId, status);
            // return ResponseEntity.ok(pedidoMapper.toResponseDTO(pedidoAtualizado));
            return ResponseEntity.ok(pedidoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar status: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro interno ao atualizar o status do pedido. Tente novamente mais tarde.");
        }
    }



    // Cancelar pedido
    @PutMapping("/{pedidoId}/cancelar")
    public ResponseEntity<?> cancelarPedido(@PathVariable Long pedidoId,
                                            @RequestParam(required = false) String motivo) {
        try {
            Pedido pedidoCancelado = pedidoService.cancelarPedido(pedidoId, motivo);
            // return ResponseEntity.ok(pedidoMapper.toResponseDTO(pedidoCancelado));
            return ResponseEntity.ok(pedidoCancelado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro ao cancelar pedido: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocorreu um erro interno ao cancelar o pedido. Tente novamente mais tarde.");
        }
    }
}