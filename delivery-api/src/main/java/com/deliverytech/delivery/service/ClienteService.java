package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.dto.ClienteResponseDTO;
import java.util.List;


public interface ClienteService {
    ClienteResponseDTO cadastrarCliente(ClienteDTO clienteDTO);
    ClienteResponseDTO buscarClientePorId(long id);
    ClienteResponseDTO buscarClientePorEmail(String email);
    ClienteResponseDTO atualizarCliente(Long id, ClienteDTO clienteDTO);
    ClienteResponseDTO ativarDesativarCliente(Long id); 
    List<ClienteResponseDTO> listarClientesAtivos();
    List<ClienteResponseDTO> buscarClientesPorNome(String nome); 
List<ClienteResponseDTO> listarTodosOsClientes();

}