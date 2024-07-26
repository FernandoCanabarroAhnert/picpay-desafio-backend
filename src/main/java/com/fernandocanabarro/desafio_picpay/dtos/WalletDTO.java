package com.fernandocanabarro.desafio_picpay.dtos;

import org.springframework.hateoas.RepresentationModel;

import com.fernandocanabarro.desafio_picpay.entities.Wallet;
import com.fernandocanabarro.desafio_picpay.services.validations.WalletCreateValid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@WalletCreateValid
public class WalletDTO extends RepresentationModel<WalletDTO>{

    private Long id;
    @NotBlank(message = "Campo Obrigatório")
    private String fullName;
    @NotBlank(message = "Campo Obrigatório")
    private String cpfCnpj;
    @Pattern(regexp = "^[A-Za-z0-9+._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",message = "Formato do Email deve ser Válido")
    private String email;
    @NotBlank(message = "Campo Obrigatório")
    private String password;
    private Double balance;
    private String walletType;

    public WalletDTO(Wallet entity){
        id = entity.getId();
        fullName = entity.getFullName();
        cpfCnpj = entity.getCpfCnpj();
        email = entity.getEmail();
        password = entity.getPassword();
        balance = entity.getBalance();
        walletType = entity.getWalletType().getDescription();
    }
}
