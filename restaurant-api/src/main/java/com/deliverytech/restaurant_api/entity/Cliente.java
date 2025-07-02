package com.deliverytech.restaurant_api.entity;

import jakarta.persistence.Entity; // Or javax.persistence.Entity if you are using Java EE/Jakarta EE 8
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    private Long id; // Or int, UUID, etc., depending on your needs

    // Other fields for your Cliente entity (e.g., nome, email, telefone)
    private String nome;
    private String email;
    private String telefone;

    // Constructors (default and parameterized)
    public Cliente() {
    }

    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}