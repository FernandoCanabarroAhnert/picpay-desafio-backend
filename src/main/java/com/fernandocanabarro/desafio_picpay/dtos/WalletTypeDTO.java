package com.fernandocanabarro.desafio_picpay.dtos;

import com.fernandocanabarro.desafio_picpay.entities.WalletType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WalletTypeDTO {

    private Long id;
    private String descricao;

    public WalletTypeDTO(WalletType entity){
        id = entity.getId();
        descricao = entity.getDescription();
    }
}
