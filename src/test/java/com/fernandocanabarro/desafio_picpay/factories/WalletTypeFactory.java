package com.fernandocanabarro.desafio_picpay.factories;

import com.fernandocanabarro.desafio_picpay.entities.WalletType;

public class WalletTypeFactory {

    public static WalletType createWalletTypeUser(){
        return new WalletType(1L,"USER");
    }

    public static WalletType createWalletTypeMerchant(){
        return new WalletType(2L,"MERCHANT");
    }
}

