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
import com.fernandocanabarro.desafio_picpay.dtos.WalletDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class WalletControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private WalletDTO user;

    @BeforeEach
    public void setup() throws Exception{
        user = new WalletDTO(null,"Fernando", "13579246801", "fernando@gmail.com", "123456",50.0, "USER");
    }

    @Test
    public void findAllShouldReturnHttpStatus200() throws Exception{
        mockMvc.perform(get("/wallets")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0].id").value(1L))
            .andExpect(jsonPath("$.content[0].fullName").value("Maria"))
            .andExpect(jsonPath("$.content[0].cpfCnpj").value("12345678910"))
            .andExpect(jsonPath("$.content[0].email").value("maria@gmail.com"))
            .andExpect(jsonPath("$.content[0].password").value("123456"))
            .andExpect(jsonPath("$.content[0].balance").value(100.0))
            .andExpect(jsonPath("$.content[0].walletType").value("USER"));
    }

    @Test
    public void createWalletShouldReturnHttpStatus201WhenDataIsValidAndWalletTypeIsUser() throws Exception{
        mockMvc.perform(post("/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(4L))
            .andExpect(jsonPath("$.fullName").value(user.getFullName()))
            .andExpect(jsonPath("$.cpfCnpj").value(user.getCpfCnpj()))
            .andExpect(jsonPath("$.email").value(user.getEmail()))
            .andExpect(jsonPath("$.password").value(user.getPassword()))
            .andExpect(jsonPath("$.balance").value(50.0))
            .andExpect(jsonPath("$.walletType").value(user.getWalletType()))
            .andExpect(jsonPath("_links").exists());
    }

    @Test
    public void createWalletShouldReturnHttpStatus201WhenDataIsValidAndWalletTypeIsMerchant() throws Exception{
        user.setWalletType("MERCHANT");
        mockMvc.perform(post("/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(5L))
            .andExpect(jsonPath("$.fullName").value(user.getFullName()))
            .andExpect(jsonPath("$.cpfCnpj").value(user.getCpfCnpj()))
            .andExpect(jsonPath("$.email").value(user.getEmail()))
            .andExpect(jsonPath("$.password").value(user.getPassword()))
            .andExpect(jsonPath("$.balance").value(50.0))
            .andExpect(jsonPath("$.walletType").value(user.getWalletType()));
    }

    @Test
    public void createWalletShouldReturnHttpStatus422WhenFullNameIsBlank() throws Exception{
        user.setFullName("");
        mockMvc.perform(post("/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("fullName"))
            .andExpect(jsonPath("$.errors[0].message").value("Campo Obrigatório"));
    }

    @Test
    public void createWalletShouldReturnHttpStatus422WhenCpfCnpjIsBlank() throws Exception{
        user.setCpfCnpj("");
        mockMvc.perform(post("/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("cpfCnpj"))
            .andExpect(jsonPath("$.errors[0].message").value("Campo Obrigatório"));
    }

    @Test
    public void createWalletShouldReturnHttpStatus422WhenEmailIsInvalid() throws Exception{
        user.setEmail("adhgjhadk");
        mockMvc.perform(post("/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("email"))
            .andExpect(jsonPath("$.errors[0].message").value("Formato do Email deve ser Válido"));
    }

    @Test
    public void createWalletShouldReturnHttpStatus422WhenPasswordIsBlank() throws Exception{
        user.setPassword(" ");
        mockMvc.perform(post("/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("password"))
            .andExpect(jsonPath("$.errors[0].message").value("Campo Obrigatório"));
    }

    @Test
    public void createWalletShouldReturnHttpStatus422WhenWalletTypeIsBlank() throws Exception{
        user.setWalletType("");
        mockMvc.perform(post("/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("walletType"))
            .andExpect(jsonPath("$.errors[0].message").value("Tipo de Carteira Inválida"));
    }

    @Test
    public void createWalletShouldReturnHttpStatus422WhenEmailAlreadyExistsInDatabase() throws Exception{
        user.setEmail("maria@gmail.com");
        mockMvc.perform(post("/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("email"))
            .andExpect(jsonPath("$.errors[0].message").value("Este Email já Existe"));
    }

    @Test
    public void createWalletShouldReturnHttpStatus422WhenCpfCnpjAlreadyExistsInDatabase() throws Exception{
        user.setCpfCnpj("12345678910");
        mockMvc.perform(post("/wallets")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isUnprocessableEntity())
            .andExpect(jsonPath("$.errors[0].fieldName").value("cpfCnpj"))
            .andExpect(jsonPath("$.errors[0].message").value("Este Documento já Existe"));
    }



}
