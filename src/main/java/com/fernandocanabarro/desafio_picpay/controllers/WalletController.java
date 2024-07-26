package com.fernandocanabarro.desafio_picpay.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fernandocanabarro.desafio_picpay.dtos.WalletDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @PostMapping
    public ResponseEntity<WalletDTO> createWallet(@RequestBody @Valid WalletDTO dto){
        return null;
    }

    @GetMapping
    public ResponseEntity<Page<WalletDTO>> findAll(Pageable pageable){
        return null;
    }
}
