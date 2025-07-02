package com.deliverytech.restaurant_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Version;
import java.util.List;

// User entity is in the same package; no import needed

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	@ManyToOne
	private User customer;

	public User getCustomer() {
		return customer;
	}

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> items;

	@Version
	private Long version; // Para controle otimista de concorrência

	public Long getVersion() {
		return version;
	}

	// Getters and setters can be added here if needed
}