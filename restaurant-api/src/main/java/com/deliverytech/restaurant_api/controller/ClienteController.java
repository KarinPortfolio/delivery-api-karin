
package com.deliverytech.restaurant_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.deliverytech.restaurant_api.entity.Cliente;
import com.deliverytech.restaurant_api.repository.ClienteRepository;

@Controller
public class ClienteController {
@Autowired
private ClienteRepository clienteRepository;
@GetMapping("/clientes")
public String listar(Model model){
List<Cliente> clientes = clienteRepository.findAll();
model.addAttribute("clientes", clientes);
return "clientes";}}
//POST /clientes, GET /clientes,GET /clientes/{id}, PUT /clientes/{id}, DELETE
