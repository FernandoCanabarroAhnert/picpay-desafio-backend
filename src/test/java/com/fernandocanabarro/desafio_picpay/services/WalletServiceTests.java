package com.fernandocanabarro.desafio_picpay.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fernandocanabarro.desafio_picpay.dtos.WalletDTO;
import com.fernandocanabarro.desafio_picpay.entities.Wallet;
import com.fernandocanabarro.desafio_picpay.factories.WalletFactory;
import com.fernandocanabarro.desafio_picpay.factories.WalletTypeFactory;
import com.fernandocanabarro.desafio_picpay.repositories.WalletRepository;
import com.fernandocanabarro.desafio_picpay.repositories.WalletTypeRepository;

@ExtendWith(MockitoExtension.class)
public class WalletServiceTests {

    @InjectMocks
    private WalletService service;

    @Mock
    private WalletRepository repository;

    @Mock
    private WalletTypeRepository walletTypeRepository;

    private Wallet walletUser,walletMerchant;
    private WalletDTO dtoUser,dtoMerchant;

    @BeforeEach
    public void setup() throws Exception{
        walletUser = WalletFactory.createWalletUser();
        dtoUser = new WalletDTO(walletUser);
        walletMerchant = WalletFactory.createWalletMerchant();
        dtoMerchant = new WalletDTO(walletMerchant);
    }

    @Test
    public void findAllPagedShouldReturnPagedWalletDTO(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Wallet> page = new PageImpl<>(List.of(walletUser));
        when(repository.findAll(pageable)).thenReturn(page);

        Page<WalletDTO> response = service.findAll(pageable);

        assertThat(response.getNumberOfElements()).isEqualTo(1);
        assertThat(response.getContent().get(0).getId()).isEqualTo(1L);
        assertThat(response.getContent().get(0).getFullName()).isEqualTo("Maria");
        assertThat(response.getContent().get(0).getCpfCnpj()).isEqualTo("12345678910");
        assertThat(response.getContent().get(0).getEmail()).isEqualTo("maria@gmail.com");
        assertThat(response.getContent().get(0).getPassword()).isEqualTo("123456");
        assertThat(response.getContent().get(0).getBalance()).isEqualTo(100.0);
        assertThat(response.getContent().get(0).getWalletType()).isEqualTo("USER");
    }

    @Test
    public void createWalletShouldReturnWalletDTOWhenWalletTypeIsUserAndDataIsValid(){
        when(walletTypeRepository.findByDescription(dtoUser.getWalletType())).thenReturn(WalletTypeFactory.createWalletTypeUser());
        when(repository.save(any(Wallet.class))).thenReturn(walletUser);

        WalletDTO response = service.createWallet(dtoUser);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getFullName()).isEqualTo("Maria");
        assertThat(response.getCpfCnpj()).isEqualTo("12345678910");
        assertThat(response.getEmail()).isEqualTo("maria@gmail.com");
        assertThat(response.getPassword()).isEqualTo("123456");
        assertThat(response.getBalance()).isEqualTo(100.0);
        assertThat(response.getWalletType()).isEqualTo("USER");
    }

    @Test
    public void createWalletShouldReturnWalletDTOWhenWalletTypeIsMerchantAndDataIsValid(){
        when(walletTypeRepository.findByDescription(dtoMerchant.getWalletType())).thenReturn(WalletTypeFactory.createWalletTypeMerchant());
        when(repository.save(any(Wallet.class))).thenReturn(walletMerchant);

        WalletDTO response = service.createWallet(dtoMerchant);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(2L);
        assertThat(response.getFullName()).isEqualTo("Alex");
        assertThat(response.getCpfCnpj()).isEqualTo("01987654321");
        assertThat(response.getEmail()).isEqualTo("alex@gmail.com");
        assertThat(response.getPassword()).isEqualTo("123456");
        assertThat(response.getBalance()).isEqualTo(70.0);
        assertThat(response.getWalletType()).isEqualTo("MERCHANT");
    }

  
}
