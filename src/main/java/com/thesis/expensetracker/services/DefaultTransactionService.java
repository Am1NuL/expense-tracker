package com.thesis.expensetracker.services;

import com.thesis.expensetracker.exceptions.NotFoundException;
import com.thesis.expensetracker.model.Transaction;
import com.thesis.expensetracker.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DefaultTransactionService implements TransactionService {

    private final TransactionRepository transactionRepository;

    public DefaultTransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        Objects.requireNonNull(id, "Transaction id is required!");
        return transactionRepository.findById(id)
                .orElseThrow(() -> {
                    String message = "Transaction not found. ID: " + id;
                    log.error(message);
                    throw new NotFoundException(message);
                });
    }

    @Override
    public Transaction save(Transaction object) {
        return transactionRepository.save(object);
    }

    @Override
    public void delete(Transaction object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<Transaction> findAllByWalletId(Long walletId) {
        return transactionRepository.findAllByWalletId(walletId);
    }
}
