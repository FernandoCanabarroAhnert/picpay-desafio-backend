package com.fernandocanabarro.desafio_picpay.services.exceptions;

public class ForbiddenException extends RuntimeException{

    public ForbiddenException(String msg){
        super(msg);
    }
}
