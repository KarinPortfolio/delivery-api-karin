package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.dto.ClienteResponseDTO;
import com.deliverytech.delivery.exception.BusinessException; // Importe a exceção de negócio
import com.deliverytech.delivery.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; 
import jakarta.persistence.EntityNotFoundException; 

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*") 
public class ClienteController {

    private final ClienteService clienteService; 
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    // Mapeia para /clientes/todos
    @GetMapping("/todos") 
public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
    List<ClienteResponseDTO> clientes = clienteService.listarTodosOsClientes(); // <-- Você precisará criar este método no seu serviço
    return ResponseEntity.ok(clientes); }

    // Cadastrar novo cliente
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteDTO dto) {
        try {
            ClienteResponseDTO clienteSalvo = clienteService.cadastrarCliente(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
        } catch (BusinessException e) { 
            return ResponseEntity.badRequest().body(new ClienteResponseDTO());
           
        } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ClienteResponseDTO()); 
        }
    }

    // Listar todos os clientes ativos
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarAtivos() {
        List<ClienteResponseDTO> clientes = clienteService.listarClientesAtivos();
        return ResponseEntity.ok(clientes);
    }

    // Buscar cliente por ID     
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable long id) { 
        try {
            ClienteResponseDTO cliente = clienteService.buscarClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (EntityNotFoundException e) { 
            return ResponseEntity.notFound().build(); 
        }
    }

    // Atualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody ClienteDTO dto) { 
        try {
            ClienteResponseDTO clienteAtualizado = clienteService.atualizarCliente(id, dto); 
            return ResponseEntity.ok(clienteAtualizado);
        } catch (EntityNotFoundException e) { 
            return ResponseEntity.notFound().build();
        } catch (BusinessException e) { 
            return ResponseEntity.badRequest().body(new ClienteResponseDTO()); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ClienteResponseDTO()); 
        }
    }
    // Ativar/Desativar cliente
    @PatchMapping("/{id}/toggle-status") 
    public ResponseEntity<ClienteResponseDTO> ativarDesativar(@PathVariable Long id) { 
        try {
            ClienteResponseDTO clienteAtualizado = clienteService.ativarDesativarCliente(id);
            return ResponseEntity.ok(clienteAtualizado);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ClienteResponseDTO()); 
        }
    }

    // Buscar clientes por nome
    @GetMapping("/nome") 
    public ResponseEntity<List<ClienteResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<ClienteResponseDTO> clientes = clienteService.buscarClientesPorNome(nome); 
        return ResponseEntity.ok(clientes);
    }

    // Buscar cliente por email
    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteResponseDTO> buscarPorEmail(@PathVariable String email) {
        try {
            ClienteResponseDTO cliente = clienteService.buscarClientePorEmail(email);
            return ResponseEntity.ok(cliente);
        } catch (EntityNotFoundException e) { 
            return ResponseEntity.notFound().build();
        }
    }
}