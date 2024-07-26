package com.fernandocanabarro.desafio_picpay.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fernandocanabarro.desafio_picpay.dtos.TransactionRequestDTO;
import com.fernandocanabarro.desafio_picpay.dtos.TransactionResponseDTO;
import com.fernandocanabarro.desafio_picpay.services.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfer")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransfer(@Valid @RequestBody TransactionRequestDTO dto){
        TransactionResponseDTO obj = service.createTransfer(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
