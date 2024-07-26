package com.fernandocanabarro.desafio_picpay.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernandocanabarro.desafio_picpay.dtos.TransactionRequestDTO;
import com.fernandocanabarro.desafio_picpay.dtos.TransactionResponseDTO;
import com.fernandocanabarro.desafio_picpay.entities.Transaction;
import com.fernandocanabarro.desafio_picpay.entities.Wallet;
import com.fernandocanabarro.desafio_picpay.repositories.TransactionRepository;
import com.fernandocanabarro.desafio_picpay.repositories.WalletRepository;

@Service
public class TransactionService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private NotificationService notificationService;

    @Transactional
    public TransactionResponseDTO createTransfer(TransactionRequestDTO dto){
        Transaction transaction = new Transaction();

        Wallet payer = walletRepository.findById(dto.getPayerId()).get();
        
        Wallet payee = walletRepository.findById(dto.getPayeeId()).get();

        transaction.setPayer(payer);
        transaction.setPayee(payee);
        transaction.setValue(dto.getValue());
        transaction.setMoment(Instant.now());

        payer.decreaseBalance(dto.getValue());
        payee.increaseBalance(dto.getValue());

        authorizationService.isTransactionAuthorized();

        walletRepository.save(payer);
        walletRepository.save(payee);
        transaction = repository.save(transaction);

        notificationService.sendNotification(payer, String.format("Transação Concluída! Saldo Atual = R$%.2f", payer.getBalance()));
        notificationService.sendNotification(payee, String.format("Transação Concluída! Saldo Atual = R$%.2f", payee.getBalance()));

        return new TransactionResponseDTO(transaction);
    }


}
