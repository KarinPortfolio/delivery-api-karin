package com.deliverytech.delivery.config; // Certifique-se que o pacote está correto

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Indica que esta classe contém definições de beans
public class ModelMapperConfig {

    @Bean // Indica que o método retorna um bean que será gerenciado pelo Spring
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}