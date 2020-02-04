package com.thesis.expensetracker.services;


import com.thesis.expensetracker.model.Expense;
import com.thesis.expensetracker.model.Income;
import com.thesis.expensetracker.model.Transaction;
import com.thesis.expensetracker.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultTransactionServiceTest {

    private final static Long TRANSACTION_ID = 1L;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private DefaultTransactionService transactionService;

    @Test
    public void findById() {
        Transaction expense = new Expense();
        expense.setId(TRANSACTION_ID);

        when(transactionRepository.findById(any())).thenReturn(Optional.of(expense));

        Transaction transaction = transactionRepository.findById(TRANSACTION_ID).get();

        assertEquals(TRANSACTION_ID, transaction.getId());

        verify(transactionRepository).findById(any());
    }

    @Test
    public void findAll() {
        List<Transaction> returnTransactionList = new ArrayList<>();
        returnTransactionList.add(new Expense());
        returnTransactionList.add(new Income());

        when(transactionRepository.findAll()).thenReturn(returnTransactionList);

        List<Transaction> transactions = transactionRepository.findAll();

        assertNotNull(transactions);
        assertEquals(returnTransactionList.size(), transactions.size());

        verify(transactionRepository).findAll();
    }

    @Test
    void save() {
        Transaction expenseToSave = new Expense();
        expenseToSave.setId(TRANSACTION_ID);

        when(transactionRepository.save(any())).thenReturn(expenseToSave);

        Transaction savedPost = transactionRepository.save(new Expense());

        assertNotNull(savedPost);

        verify(transactionRepository).save(any());
    }

    @Test
    void findAllByWalletId() {
    }

    @Test
    void getTotalExpenses() {
    }

    @Test
    void getTotalIncome() {

    }
}