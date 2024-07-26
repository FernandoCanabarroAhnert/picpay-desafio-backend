package com.fernandocanabarro.desafio_picpay.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernandocanabarro.desafio_picpay.dtos.TransactionRequestDTO;
import com.fernandocanabarro.desafio_picpay.factories.TransactionFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class TransactionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private TransactionRequestDTO dto;

    @BeforeEach
    public void setup() throws Exception{
        dto = TransactionFactory.createTransactionRequest();
    }

    @Test
    public void createTransferShouldReturnHttpStatus201WhenDataIsValid() throws Exception{
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.payer.id").value(1L))
            .andExpect(jsonPath("$.payer.fullName").value("Maria"))
            .andExpect(jsonPath("$.payer.email").value("maria@gmail.com"))
            .andExpect(jsonPath("$.payer.balance").value(90.0))
            .andExpect(jsonPath("$.payee.id").value(2L))
            .andExpect(jsonPath("$.payee.fullName").value("Alex"))
            .andExpect(jsonPath("$.payee.email").value("alex@gmail.com"))
            .andExpect(jsonPath("$.payee.balance").value(80.0))
            .andExpect(jsonPath("$.value").value(10.0));
    }

    @Test
    public void createTransferShouldReturnHttpStatus403WhenPayerIsMerchant() throws Exception{
        dto.setPayerId(2L);
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isForbidden());
    }

    @Test
    public void createTransferShouldReturnHttpStatus403WhenPayerIdIsEqualToPayeeId() throws Exception{
        dto.setPayeeId(1L);
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isForbidden());
    }

    @Test
    public void createTransferShouldReturnHttpStatus404WhenPayerIdDoesNotExist() throws Exception{
        dto.setPayerId(5L);
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void createTransferShouldReturnHttpStatus404WhenPayeeIdDoesNotExist() throws Exception{
        dto.setPayeeId(5L);
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isNotFound());
    }

    @Test
    public void createTransferShouldReturnHttpStatus422WhenValueIsGreaterThanPayersBalance() throws Exception{
        dto.setValue(1000.0);
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("value"))
            .andExpect(jsonPath("$.errors[0].message").value("Saldo Insuficiente"));
    }

    @Test
    public void createTransferShouldReturnHttpStatus422WhenValueIsNotPositive() throws Exception{
        dto.setValue(0.0);
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("value"))
            .andExpect(jsonPath("$.errors[0].message").value("Valor deve ser maior que 0"));
    }

    @Test
    public void createTransferShouldReturnHttpStatus422WhenValueIsNull() throws Exception{
        dto.setValue(null);
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createTransferShouldReturnHttpStatus422WhenPayerIdIsNull() throws Exception{
        dto.setPayerId(null);
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void createTransferShouldReturnHttpStatus422WhenPayeeIdIsNull() throws Exception{
        dto.setPayeeId(null);
        mockMvc.perform(post("/transfer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isUnprocessableEntity());
    }
}
