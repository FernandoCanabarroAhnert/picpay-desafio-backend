package com.fernandocanabarro.desafio_picpay.factories;

import java.time.Instant;

import com.fernandocanabarro.desafio_picpay.dtos.TransactionRequestDTO;
import com.fernandocanabarro.desafio_picpay.entities.Transaction;
import com.fernandocanabarro.desafio_picpay.entities.Wallet;

public class TransactionFactory {

    private static Wallet payer = WalletFactory.createWalletUser();
    private static Wallet payee = WalletFactory.createWalletMerchant();

    public static TransactionRequestDTO createTransactionRequest(){
        return new TransactionRequestDTO(1L, 2L, 10.0);
    }

    public static Transaction createTransaction(){
        return new Transaction(1L,payer , payee, 10.0, Instant.now());
    }
}
