package com.fernandocanabarro.desafio_picpay.services.exceptions;

public class NotificationException extends RuntimeException{

    public NotificationException(){
        super("Serviço de Notificação está Fora do Ar");
    }
}
