package com.deliverytech.restaurant_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverytech.restaurant_api.entity.Restaurante; // Optional but good practice

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    // Spring Data JPA provides basic CRUD methods.
}
//Buscar por nome, categoria, ativos, ordenação por avaliação