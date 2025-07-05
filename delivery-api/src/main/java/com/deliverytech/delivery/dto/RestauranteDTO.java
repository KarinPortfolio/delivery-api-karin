package com.deliverytech.delivery.dto;

import java.math.BigDecimal; // Para a taxa de entrega

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class RestauranteDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "A categoria não pode estar em branco")
    @Size(max = 50, message = "A categoria deve ter no máximo 50 caracteres")
    private String categoria; // Ex: "Pizzaria", "Japonesa", "Hamburgueria"

    @NotBlank(message = "O endereço não pode estar em branco")
    @Size(max = 255, message = "O endereço deve ter no máximo 255 caracteres")
    private String endereco;

    @NotBlank(message = "O telefone não pode estar em branco")
    @Pattern(regexp = "^\\(\\d{2}\\)\\d{4,5}-\\d{4}$", message = "Formato de telefone inválido. Use (XX)XXXXX-XXXX")
    private String telefone;

    @NotBlank(message = "O CNPJ não pode estar em branco")
    @Pattern(regexp = "^\\d{14}$", message = "O CNPJ deve conter exatamente 14 dígitos numéricos")
    private String cnpj; // CNPJ deve ser enviado sem formatação, apenas os 14 dígitos

    @NotNull(message = "A taxa de entrega não pode ser nula")
    @PositiveOrZero(message = "A taxa de entrega deve ser um valor positivo ou zero")
    private BigDecimal taxaEntrega; // Valor da taxa de entrega do restaurante

}