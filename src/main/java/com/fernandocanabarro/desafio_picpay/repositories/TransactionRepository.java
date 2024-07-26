package com.fernandocanabarro.desafio_picpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernandocanabarro.desafio_picpay.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
