// src/main/java/com/deliverytech/delivery/service/ClienteServiceImpl.java
package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.dto.ClienteResponseDTO;
import com.deliverytech.delivery.entity.Cliente;
import com.deliverytech.delivery.exception.BusinessException;
import com.deliverytech.delivery.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    // Construtor com injeção de dependências
    public ClienteServiceImpl(ClienteRepository clienteRepository, ModelMapper modelMapper) {
        this.clienteRepository = clienteRepository;
        this.modelMapper = modelMapper;
    }

    // Implementação dos métodos da interface ClienteService

    @Override
    @Transactional
    public ClienteResponseDTO cadastrarCliente(ClienteDTO dto) {
        if (clienteRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado: " + dto.getEmail());
        }
        Cliente cliente = modelMapper.map(dto, Cliente.class);
        cliente.setAtivo(true);
        Cliente salvo = clienteRepository.save(cliente);
        return modelMapper.map(salvo, ClienteResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true) // Boa prática para métodos de leitura
    public ClienteResponseDTO buscarClientePorId(long id) { // Usando 'long' para corresponder à interface
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true) // Boa prática para métodos de leitura
    public ClienteResponseDTO buscarClientePorEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado com o e-mail: " + email));
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    @Override
    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));

        // Permite que o email permaneça o mesmo, mas impede que seja alterado para um email já existente por outro cliente
        if (!cliente.getEmail().equals(dto.getEmail()) && clienteRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Email já cadastrado: " + dto.getEmail());
        }

        // Atualiza os campos do cliente existente com os dados do DTO
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        Cliente atualizado = clienteRepository.save(cliente);
        return modelMapper.map(atualizado, ClienteResponseDTO.class);
    }

    @Override
    @Transactional
    public ClienteResponseDTO ativarDesativarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));

        cliente.setAtivo(!cliente.isAtivo()); 
        Cliente salvo = clienteRepository.save(cliente);
        return modelMapper.map(salvo, ClienteResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true) 
    public List<ClienteResponseDTO> listarClientesAtivos() {
        return clienteRepository.findByAtivoTrue()
                .stream()
                .map(c -> modelMapper.map(c, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true) 
    public List<ClienteResponseDTO> buscarClientesPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(c -> modelMapper.map(c, ClienteResponseDTO.class))
                .collect(Collectors.toList());
    }
@Override
@Transactional(readOnly = true)
public List<ClienteResponseDTO> listarTodosOsClientes() {
    return clienteRepository.findAll()
            .stream()
            .map(c -> modelMapper.map(c, ClienteResponseDTO.class))
            .collect(Collectors.toList());
}

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
}