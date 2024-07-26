package com.fernandocanabarro.desafio_picpay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernandocanabarro.desafio_picpay.controllers.TransactionController;
import com.fernandocanabarro.desafio_picpay.controllers.WalletController;
import com.fernandocanabarro.desafio_picpay.dtos.WalletDTO;
import com.fernandocanabarro.desafio_picpay.entities.Wallet;
import com.fernandocanabarro.desafio_picpay.repositories.WalletRepository;
import com.fernandocanabarro.desafio_picpay.repositories.WalletTypeRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class WalletService {

    @Autowired
    private WalletRepository repository;

    @Autowired
    private WalletTypeRepository walletTypeRepository;

    @Transactional
    public WalletDTO createWallet(WalletDTO dto){
        Wallet entity = new Wallet();
        toEntity(entity,dto);
        entity = repository.save(entity);
        return (dto.getWalletType().equals("USER")) 
            ? new WalletDTO(entity).add(linkTo(methodOn(TransactionController.class)
                .createTransfer(null)).withRel("Realizar Transferência"))
            : new WalletDTO(entity);
    }

    private void toEntity(Wallet entity, WalletDTO dto) {
        entity.setFullName(dto.getFullName());
        entity.setCpfCnpj(dto.getCpfCnpj());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setBalance(50.0);
        entity.setWalletType(walletTypeRepository.findByDescription(dto.getWalletType()));
    }

    @Transactional(readOnly = true)
    public Page<WalletDTO> findAll(Pageable pageable){
        return repository.findAll(pageable).map(x -> new WalletDTO(x).add(linkTo(methodOn(WalletController.class)
        .findAll(null)).withSelfRel()));
    }
}
