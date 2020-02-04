package com.thesis.expensetracker.services;

import com.thesis.expensetracker.model.Transaction;

import java.util.List;

public interface TransactionService extends CrudService<Transaction, Long> {
    List<Transaction> findAllByWalletId(Long walletId);
}
