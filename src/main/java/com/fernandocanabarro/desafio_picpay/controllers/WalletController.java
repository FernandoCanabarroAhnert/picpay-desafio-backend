package com.fernandocanabarro.desafio_picpay.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fernandocanabarro.desafio_picpay.dtos.WalletDTO;
import com.fernandocanabarro.desafio_picpay.services.WalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService service;

    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(@RequestBody @Valid WalletDTO dto){
        dto = service.createWallet(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<WalletDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable));
    }
}
