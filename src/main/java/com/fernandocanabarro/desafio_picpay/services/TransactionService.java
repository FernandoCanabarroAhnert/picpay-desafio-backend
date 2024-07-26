package com.fernandocanabarro.desafio_picpay.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernandocanabarro.desafio_picpay.dtos.TransactionRequestDTO;
import com.fernandocanabarro.desafio_picpay.dtos.TransactionResponseDTO;

@Service
public class TransactionService {

    @Transactional
    public TransactionResponseDTO createTransfer(TransactionRequestDTO dto){
        return null;
    }


}
