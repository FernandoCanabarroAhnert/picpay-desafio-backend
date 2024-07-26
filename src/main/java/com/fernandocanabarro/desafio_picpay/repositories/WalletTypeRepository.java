package com.fernandocanabarro.desafio_picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernandocanabarro.desafio_picpay.entities.WalletType;

@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType,Long>{

    WalletType findByDescription(String description);
}
