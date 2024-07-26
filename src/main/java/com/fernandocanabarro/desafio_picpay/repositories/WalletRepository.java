package com.fernandocanabarro.desafio_picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernandocanabarro.desafio_picpay.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long>{

    Wallet findByCpfCnpj(String cpfCnpj);

    Wallet findByEmail(String email);
}
