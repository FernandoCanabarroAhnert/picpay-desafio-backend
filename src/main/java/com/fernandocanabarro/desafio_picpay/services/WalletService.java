package com.fernandocanabarro.desafio_picpay.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernandocanabarro.desafio_picpay.dtos.WalletDTO;

@Service
public class WalletService {

    @Transactional
    public WalletDTO createWallet(WalletDTO dto){
        return null;
    }

    @Transactional(readOnly = true)
    public Page<WalletDTO> findAll(Pageable pageable){
        return null;
    }
}
