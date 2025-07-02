package com.deliverytech.restaurant_api.repository;

import org.springframework.data.jpa.repository.JpaRepository; // Import your Cliente entity
import org.springframework.stereotype.Repository;

import com.deliverytech.restaurant_api.entity.Cliente; // Optional but good practice for clarity

@Repository // Tells Spring this is a repository component
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // JpaRepository provides methods like findAll(), findById(), save(), delete(), etc.
    // The first type parameter is your Entity class (Cliente)
    // The second type parameter is the type of your Entity's ID (Long, if your Cliente ID is Long)
}

//Estender JpaRepository
//Implementar buscas por e-mail e status ativo