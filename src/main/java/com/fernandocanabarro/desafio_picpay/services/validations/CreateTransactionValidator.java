package com.fernandocanabarro.desafio_picpay.services.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fernandocanabarro.desafio_picpay.dtos.TransactionRequestDTO;
import com.fernandocanabarro.desafio_picpay.dtos.exceptions.FieldMessage;
import com.fernandocanabarro.desafio_picpay.entities.Wallet;
import com.fernandocanabarro.desafio_picpay.repositories.WalletRepository;
import com.fernandocanabarro.desafio_picpay.services.exceptions.ForbiddenException;
import com.fernandocanabarro.desafio_picpay.services.exceptions.ResourceNotFoundException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CreateTransactionValidator implements ConstraintValidator<CreateTransactionValid, TransactionRequestDTO>{

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public void initialize(CreateTransactionValid ann){}

    @Override
    public boolean isValid(TransactionRequestDTO obj, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();

        Wallet payer = walletRepository.findById(obj.getPayerId()).orElseThrow(() -> 
                    new ResourceNotFoundException("Payer Id não Encontrado!"));

        if (payer.getWalletType().getDescription().equals("MERCHANT")) {
            throw new ForbiddenException("Lojistas não podem realizar Transações");
        }
        
        Wallet payee = walletRepository.findById(obj.getPayeeId()).orElseThrow(() -> 
                    new ResourceNotFoundException("Payee Id não Encontrado!"));

        if (payer.getBalance() < obj.getValue()) {
            errors.add(new FieldMessage("value", "Saldo Insuficiente"));
        }

        if (payer.getId() == payee.getId()) {
            throw new ForbiddenException("O Usuário não pode realizar transações para si mesmo");
        }

        for (FieldMessage f : errors){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(f.getMessage())
                .addPropertyNode(f.getFieldName())
                .addConstraintViolation();
        }
        return errors.isEmpty();
    }

}
