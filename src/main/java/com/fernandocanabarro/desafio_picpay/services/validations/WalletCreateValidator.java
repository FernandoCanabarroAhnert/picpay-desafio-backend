package com.fernandocanabarro.desafio_picpay.services.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fernandocanabarro.desafio_picpay.dtos.WalletDTO;
import com.fernandocanabarro.desafio_picpay.dtos.exceptions.FieldMessage;
import com.fernandocanabarro.desafio_picpay.entities.Wallet;
import com.fernandocanabarro.desafio_picpay.entities.WalletType;
import com.fernandocanabarro.desafio_picpay.repositories.WalletRepository;
import com.fernandocanabarro.desafio_picpay.repositories.WalletTypeRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class WalletCreateValidator implements ConstraintValidator<WalletCreateValid,WalletDTO>{

    @Autowired
    private WalletRepository repository;

    @Autowired
    private WalletTypeRepository walletTypeRepository;

    @Override
    public void initialize(WalletCreateValid ann){}

    @Override
    public boolean isValid(WalletDTO obj, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();

        Wallet cpfCnpj = repository.findByCpfCnpj(obj.getCpfCnpj());
        if (cpfCnpj != null) {
            errors.add(new FieldMessage("cpfCnpj", "Este Documento já Existe"));
        }

        Wallet email = repository.findByEmail(obj.getEmail());
        if (email != null) {
            errors.add(new FieldMessage("email", "Este Email já Existe"));
        }

        WalletType walletType = walletTypeRepository.findByDescription(obj.getWalletType());
        if (walletType == null) {
            errors.add(new FieldMessage("walletType", "Tipo de Carteira Inválida"));
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
