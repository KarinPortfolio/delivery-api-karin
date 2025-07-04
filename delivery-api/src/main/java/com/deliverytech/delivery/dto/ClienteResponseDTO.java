package com.deliverytech.delivery.dto;

import lombok.Data; 

// DTO (Data Transfer Object) usado para retornar dados do cliente em respostas da API
@Data 
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private Boolean ativo; // Campo para indicar se o cliente está ativo ou inativo
}