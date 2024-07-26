package com.fernandocanabarro.desafio_picpay.dtos.client;

public class Data {

    private Boolean authorization;

    public Data(){}

    public Data(boolean authorization){
        this.authorization = authorization;
    }

    public boolean isAuthorized(){
        return this.authorization;
    }

    public void setAuthorization(Boolean authorization) {
        this.authorization = authorization;
    }

}
