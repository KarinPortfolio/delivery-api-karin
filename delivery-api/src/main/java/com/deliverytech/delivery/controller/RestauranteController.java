package com.deliverytech.delivery.controller;

import java.util.List;
import java.util.Optional; // Importar Optional

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Importar HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; // Importar PutMapping
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.entity.Restaurante;
import com.deliverytech.delivery.repository.RestauranteRepository;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Restaurante> criar(@RequestBody Restaurante restaurante) {
        // Para POST, o ID deve ser nulo ou 0 para que o JPA insira um novo registro
        if (restaurante.getId() != null && restaurante.getId() != 0) {
            return ResponseEntity.badRequest().build(); // Não é uma criação válida se já tem ID
        }
        Restaurante novoRestaurante = restauranteRepository.save(restaurante);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        return restauranteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // atualizar restaurante
    @PutMapping("/{id}") // Mapeia requisições PUT para /api/restaurantes/{id}
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody Restaurante restauranteAtualizado) {
        // Verifica se o restaurante existe
        Optional<Restaurante> restauranteExistente = restauranteRepository.findById(id);

        if (restauranteExistente.isEmpty()) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrar
        }

        Restaurante restaurante = restauranteExistente.get();

        // Atualiza apenas os campos que você deseja permitir serem atualizados
        restaurante.setNome(restauranteAtualizado.getNome());
        restaurante.setCnpj(restauranteAtualizado.getCnpj());
        restaurante.setEndereco(restauranteAtualizado.getEndereco());
        restaurante.setTelefone(restauranteAtualizado.getTelefone());
        restaurante.setTaxaEntrega(restauranteAtualizado.getTaxaEntrega());
        restaurante.setAtivo(restauranteAtualizado.isAtivo()); 
        restaurante.setCategoria(restauranteAtualizado.getCategoria());
        restaurante.setAvaliacao(restauranteAtualizado.getAvaliacao());


        // Salva o restaurante atualizado no banco de dados
        Restaurante salvo = restauranteRepository.save(restaurante);
        return ResponseEntity.ok(salvo); // Retorna 200 OK com o objeto atualizado
    }
}