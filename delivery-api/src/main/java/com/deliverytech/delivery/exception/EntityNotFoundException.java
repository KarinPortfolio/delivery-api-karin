package com.deliverytech.delivery.exception;

public class EntityNotFoundException extends RuntimeException {
    //Construtor que recebe a mensagem de erro
    public EntityNotFoundException(String msg){
        super(msg);
    }
    
}
