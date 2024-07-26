package com.fernandocanabarro.desafio_picpay.openapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.fernandocanabarro.desafio_picpay.dtos.TransactionRequestDTO;
import com.fernandocanabarro.desafio_picpay.dtos.TransactionResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface TransactionControllerOpenAPI {

    @Operation(
    description = "Realizar Transferência",
    summary = "Endpoint responsável por receber a requisição da Transação",
    responses = {
         @ApiResponse(description = "Transferência Criada", responseCode = "201"),
         @ApiResponse(description = "Serviço de Notificação Fora do Ar", responseCode = "400"),
         @ApiResponse(description = "Transação Não Autorizada", responseCode = "403"),
         @ApiResponse(description = "O Id de um dos Usuários não Existe", responseCode = "404"),
         @ApiResponse(description = "O Corpo da Requisição é Inválido ou o Usuário não possui Valor Suficiente para Realizar a Transação", responseCode = "422")
   		}
	)
    ResponseEntity<TransactionResponseDTO> createTransfer(@RequestBody @Valid TransactionRequestDTO dto);
}
