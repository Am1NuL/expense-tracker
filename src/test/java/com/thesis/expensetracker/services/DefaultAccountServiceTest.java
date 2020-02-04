package com.thesis.expensetracker.services;

import com.thesis.expensetracker.model.Account;
import com.thesis.expensetracker.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultAccountServiceTest {

    private static final Long ACCOUNT_ID = 1L;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private DefaultAccountService accountService;

    @Test
    public void findAll() {
        List<Account> returnAccountList = new ArrayList<>();
        returnAccountList.add(new Account());
        returnAccountList.add(new Account());

        when(accountRepository.findAll()).thenReturn(returnAccountList);

        List<Account> transactions = accountRepository.findAll();

        assertNotNull(transactions);
        assertEquals(returnAccountList.size(), transactions.size());

        verify(accountRepository).findAll();
    }

    @Test
    void findById() {
        Account account = new Account();
        account.setId(ACCOUNT_ID);

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));

        Account transaction = accountRepository.findById(ACCOUNT_ID).get();

        assertEquals(ACCOUNT_ID, transaction.getId());

        verify(accountRepository).findById(any());
    }

    @Test
    void save() {
        Account accountToSave = new Account();
        accountToSave.setId(ACCOUNT_ID);

        when(accountRepository.save(any())).thenReturn(accountToSave);

        Account savedPost = accountRepository.save(new Account());

        assertNotNull(savedPost);

        verify(accountRepository).save(any());
    }

    @Test
    void findByEmail() {
    }
}