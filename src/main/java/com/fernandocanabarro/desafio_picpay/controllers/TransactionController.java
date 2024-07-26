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
import com.fernandocanabarro.desafio_picpay.openapi.TransactionControllerOpenAPI;
import com.fernandocanabarro.desafio_picpay.services.TransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transfer")
@Tag(name = "Transaction", description = "Controller para Transaction")
public class TransactionController implements TransactionControllerOpenAPI{

    @Autowired
    private TransactionService service;

    @PostMapping(produces = "application/json")
    public ResponseEntity<TransactionResponseDTO> createTransfer(@Valid @RequestBody TransactionRequestDTO dto){
        TransactionResponseDTO obj = service.createTransfer(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
