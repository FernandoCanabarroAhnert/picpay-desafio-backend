package com.fernandocanabarro.desafio_picpay.dtos;

import java.time.Instant;

import com.fernandocanabarro.desafio_picpay.entities.Transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDTO {

    private Long id;
    private WalletDTO payer;
    private WalletDTO payee;
    private Double value;
    private Instant moment;

    public TransactionResponseDTO(Transaction entity){
        id = entity.getId();
        payer = new WalletDTO(entity.getPayer());
        payee = new WalletDTO(entity.getPayee());
        value = entity.getValue();
        moment = entity.getMoment();
    }
}
