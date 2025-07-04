package com.deliverytech.delivery.dto;
import java.math.BigDecimal; // Para a taxa de entrega
import lombok.Data; 
import com.fasterxml.jackson.annotation.JsonGetter;

@Data 
public class RestauranteResponseDTO {

    private Long id;
    private String nome;
    private String categoria; // Ex: "Pizzaria", "Japonesa", "Hamburgueria"
    private String endereco;
    private String telefone;
    private String cnpj; 
    private BigDecimal taxaEntrega; // Valor da taxa de entrega do restaurante
    private Boolean ativo; // Indica se o restaurante está ativo/disponível

    


 @JsonGetter("cnpjFormatado") 
    public String getCnpjFormatado() {
        if (this.cnpj == null || !this.cnpj.matches("^\\d{14}$")) {
            // Retorna o CNPJ original se for nulo ou não tiver 14 dígitos (para evitar erros de formatação)
            return this.cnpj;
        }
        return this.cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}