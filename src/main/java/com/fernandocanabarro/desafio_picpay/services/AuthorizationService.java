package com.fernandocanabarro.desafio_picpay.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fernandocanabarro.desafio_picpay.dtos.client.AuthorizationResponse;
import com.fernandocanabarro.desafio_picpay.services.exceptions.ForbiddenException;

@Service
public class AuthorizationService {

    private RestTemplate restTemplate = new RestTemplate();

    public void isTransactionAuthorized(){
        String uri = "https://run.mocky.io/v3/43aa52dc-7d40-4246-a1e7-8bafef37d317";
        ResponseEntity<AuthorizationResponse> response = restTemplate.getForEntity(uri, AuthorizationResponse.class);
        if (!response.getBody().getData().isAuthorized()) {
            throw new ForbiddenException("Transação não Autorizada");
        }
    }
}
