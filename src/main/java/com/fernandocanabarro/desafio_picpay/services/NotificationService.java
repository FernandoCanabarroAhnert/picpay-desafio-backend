package com.fernandocanabarro.desafio_picpay.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fernandocanabarro.desafio_picpay.dtos.client.NotificationResponse;
import com.fernandocanabarro.desafio_picpay.entities.Wallet;
import com.fernandocanabarro.desafio_picpay.services.exceptions.NotificationException;

@Service
public class NotificationService {

    private RestTemplate restTemplate = new RestTemplate();

    public void sendNotification(Wallet wallet, String message){
        String uri = "https://run.mocky.io/v3/d7a00f28-b4e5-44d5-9c66-2eb8e290d216";
        ResponseEntity<NotificationResponse> response = restTemplate.getForEntity(uri, NotificationResponse.class);
        if (!response.getBody().getStatus().equals("sucess"))
            throw new NotificationException();
    }
}
