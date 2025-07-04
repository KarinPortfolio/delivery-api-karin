package com.deliverytech.delivery.exception;
//Exceção customizada para erros de regras de negócio
public class BusinessException extends RuntimeException{
    //Construtor que recebe a mensagem de erro
    public BusinessException(String msg){
        super(msg);
    }
}