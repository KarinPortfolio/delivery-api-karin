package com.deliverytech.restaurant_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverytech.restaurant_api.entity.Restaurante;
import com.deliverytech.restaurant_api.repository.RestauranteRepository;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    @Autowired
    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public List<Restaurante> listarAtivos() {
        // For now, assume all restaurants are "active" or implement filtering logic here
        return restauranteRepository.findAll();
    }
}//Gestão, validações, controle de status ativo/inativo
