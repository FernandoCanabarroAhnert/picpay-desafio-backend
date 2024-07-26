package com.fernandocanabarro.desafio_picpay.dtos.client;

public class AuthorizationResponse {

    private String status;
    private Data data;

    public AuthorizationResponse() {
    }

    public AuthorizationResponse(String status, Data data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    
}
