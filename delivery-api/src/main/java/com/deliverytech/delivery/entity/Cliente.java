package com.deliverytech.delivery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data; 

@Entity
@Table(name = "cliente")
@Data 
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    @Column(name = "ativo", nullable = false) 
    private boolean ativo; 

    @OneToMany(mappedBy = "cliente") 
    private List<Pedido> pedidos;

   
}