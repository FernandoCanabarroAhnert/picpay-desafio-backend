package com.fernandocanabarro.desafio_picpay.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernandocanabarro.desafio_picpay.dtos.TransactionRequestDTO;
import com.fernandocanabarro.desafio_picpay.dtos.TransactionResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfer")
public class TransactionController {

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransfer(@Valid @RequestBody TransactionRequestDTO dto){
        return null;
    }
}
