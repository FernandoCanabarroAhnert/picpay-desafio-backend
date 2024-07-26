package com.fernandocanabarro.desafio_picpay.dtos;

import com.fernandocanabarro.desafio_picpay.services.validations.CreateTransactionValid;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CreateTransactionValid
public class TransactionRequestDTO {

    private Long payerId;
    private Long payeeId;
    @Positive(message = "Valor deve ser maior que 0")
    private Double value;
}
