package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

// DTO (Data Transfer Object) usado para receber dados do cliente em requisições
public class ClienteDTO {

    // Nome obrigatório, não pode ser vazio ou nulo
    @NotBlank(message = "O nome é obrigatório.") // Adicionado mensagem de erro
    private String nome;

    // Email obrigatório, deve estar no formato válido de e-mail
    @Email(message = "O email deve ser válido.") // Adicionado mensagem de erro
    @NotBlank(message = "O email é obrigatório.") // Adicionado mensagem de erro
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$", message = "O telefone deve estar no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    // Endereço obrigatório, não pode ser vazio ou nulo
    @NotBlank(message = "O endereço é obrigatório.") // Adicionado mensagem de erro
    private String endereco;

    // Getters e setters para acessar e modificar os campos

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}