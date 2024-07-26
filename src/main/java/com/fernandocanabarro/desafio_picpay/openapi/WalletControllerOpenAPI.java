package com.fernandocanabarro.desafio_picpay.openapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.fernandocanabarro.desafio_picpay.dtos.WalletDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface WalletControllerOpenAPI {

    @Operation(
    description = "Consultar Todas as Wallets Paginadas",
    summary = "Endpoint responsável por Consultar Todas as Wallets Paginadas",
    responses = {
         @ApiResponse(description = "Consulta Realizada", responseCode = "200")
   		}
	)
    ResponseEntity<Page<WalletDTO>> findAll(Pageable pageable);

    @Operation(
    description = "Cadastrar uma nova Wallet",
    summary = "Endpoint responsável por receber a requisição de Cadastro de Wallet",
    responses = {
         @ApiResponse(description = "Wallet Criada", responseCode = "201"),
         @ApiResponse(description = "Tipo de Carteira Não Existe", responseCode = "404"),
         @ApiResponse(description = "O Corpo da Requisição é Inválido ou o CPF/CNPJ ou o Email já existem", responseCode = "422")
   		}
	)
    ResponseEntity<WalletDTO> createWallet(@RequestBody @Valid WalletDTO dto);
}
