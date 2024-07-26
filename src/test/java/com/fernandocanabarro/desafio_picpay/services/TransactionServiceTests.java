package com.fernandocanabarro.desafio_picpay.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fernandocanabarro.desafio_picpay.dtos.TransactionRequestDTO;
import com.fernandocanabarro.desafio_picpay.dtos.TransactionResponseDTO;
import com.fernandocanabarro.desafio_picpay.dtos.WalletDTO;
import com.fernandocanabarro.desafio_picpay.entities.Transaction;
import com.fernandocanabarro.desafio_picpay.entities.Wallet;
import com.fernandocanabarro.desafio_picpay.factories.TransactionFactory;
import com.fernandocanabarro.desafio_picpay.factories.WalletFactory;
import com.fernandocanabarro.desafio_picpay.repositories.TransactionRepository;
import com.fernandocanabarro.desafio_picpay.repositories.WalletRepository;
import com.fernandocanabarro.desafio_picpay.services.exceptions.ForbiddenException;
import com.fernandocanabarro.desafio_picpay.services.exceptions.NotificationException;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {

    @InjectMocks
    private TransactionService service;

    @Mock
    private TransactionRepository repository;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private AuthorizationService authorizationService;

    @Mock
    private NotificationService notificationService;

    private Wallet walletUser,walletMerchant;
    private WalletDTO dtoUser,dtoMerchant;
    private Transaction transaction;
    private TransactionRequestDTO requestDTO;

    @BeforeEach
    public void setup() throws Exception{
        walletUser = WalletFactory.createWalletUser();
        dtoUser = new WalletDTO(walletUser);
        walletMerchant = WalletFactory.createWalletMerchant();
        dtoMerchant = new WalletDTO(walletMerchant);
        transaction = TransactionFactory.createTransaction();
        requestDTO = TransactionFactory.createTransactionRequest();
    }

    @Test
    public void createTransferShouldReturnTransactionResponseDTOWhenDataIsValid(){
        when(walletRepository.findById(dtoUser.getId())).thenReturn(Optional.of(walletUser));
        when(walletRepository.findById(dtoMerchant.getId())).thenReturn(Optional.of(walletMerchant));
        when(walletRepository.save(walletUser)).thenReturn(walletUser);
        when(walletRepository.save(walletMerchant)).thenReturn(walletMerchant);
        when(repository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionResponseDTO response = service.createTransfer(requestDTO);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getPayer().getId()).isEqualTo(1L);
        assertThat(response.getPayer().getFullName()).isEqualTo("Maria");
        assertThat(response.getPayer().getEmail()).isEqualTo("maria@gmail.com");
        assertThat(response.getPayer().getWalletType()).isEqualTo("USER");
        assertThat(response.getPayee().getId()).isEqualTo(2L);
        assertThat(response.getPayee().getFullName()).isEqualTo("Alex");
        assertThat(response.getPayee().getEmail()).isEqualTo("alex@gmail.com");
        assertThat(response.getPayee().getWalletType()).isEqualTo("MERCHANT");
    }

   @Test
   public void createTransferShouldThrowForbiddenExceptionWhenTransactionIsNotAuthorized(){
        when(walletRepository.findById(dtoUser.getId())).thenReturn(Optional.of(walletUser));
        when(walletRepository.findById(dtoMerchant.getId())).thenReturn(Optional.of(walletMerchant));
        doThrow(ForbiddenException.class).when(authorizationService).isTransactionAuthorized();

        assertThatThrownBy(() -> service.createTransfer(requestDTO)).isInstanceOf(ForbiddenException.class);
   }

   @Test
   public void createTransferShouldThrowNotificationExceptionWhenNotificationServiceIsNotWorking(){
        when(walletRepository.findById(dtoUser.getId())).thenReturn(Optional.of(walletUser));
        when(walletRepository.findById(dtoMerchant.getId())).thenReturn(Optional.of(walletMerchant));
        when(walletRepository.save(walletUser)).thenReturn(walletUser);
        when(walletRepository.save(walletMerchant)).thenReturn(walletMerchant);
        when(repository.save(any(Transaction.class))).thenReturn(transaction);
        doThrow(NotificationException.class).when(notificationService).sendNotification(any(Wallet.class), anyString());

        assertThatThrownBy(() -> service.createTransfer(requestDTO)).isInstanceOf(NotificationException.class);
   }



}
