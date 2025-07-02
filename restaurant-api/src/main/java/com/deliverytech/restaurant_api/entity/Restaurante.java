package com.deliverytech.restaurant_api.entity;

import jakarta.persistence.Entity; // Or javax.persistence.Entity if using Java EE/Jakarta EE 8
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Restaurante {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    private Long id; // Choose the appropriate type for your ID (e.g., Long, UUID)

    // Other fields for your Restaurante entity (e.g., nome, endereco, cozinha)
    private String nome;
    private String endereco;
    private String cozinha;

    // Constructors (default and parameterized)
    public Restaurante() {
    }

    public Restaurante(String nome, String endereco, String cozinha) {
        this.nome = nome;
        this.endereco = endereco;
        this.cozinha = cozinha;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCozinha() {
        return cozinha;
    }

    public void setCozinha(String cozinha) {
        this.cozinha = cozinha;
    }
}