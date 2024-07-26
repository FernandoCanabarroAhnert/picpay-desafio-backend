package com.fernandocanabarro.desafio_picpay.factories;

import com.fernandocanabarro.desafio_picpay.entities.Wallet;
import com.fernandocanabarro.desafio_picpay.entities.WalletType;

public class WalletFactory {

    private static WalletType USER = WalletTypeFactory.createWalletTypeUser();

    private static WalletType MERCHANT = WalletTypeFactory.createWalletTypeMerchant();

    public static Wallet createWalletUser(){
        return new Wallet(1L,"Maria","12345678910","maria@gmail.com","123456",100.0,USER);
    }

    public static Wallet createWalletMerchant(){
        return new Wallet(2L,"Alex","01987654321","alex@gmail.com","123456",70.0,MERCHANT);
    }
}
