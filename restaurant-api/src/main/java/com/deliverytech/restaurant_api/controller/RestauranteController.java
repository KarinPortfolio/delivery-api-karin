package com.deliverytech.restaurant_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 

import com.deliverytech.restaurant_api.entity.Restaurante;
import com.deliverytech.restaurant_api.service.RestauranteService;

@RestController 
@RequestMapping("/api/v1/restaurants") 
public class RestauranteController {

    private final RestauranteService restauranteService;

    @Autowired
    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

   
    @GetMapping 
    public List<Restaurante> listar() { 
        return restauranteService.listarAtivos();
    }
}//CRUD completo + buscar por categoria
