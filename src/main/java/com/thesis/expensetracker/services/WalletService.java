package com.thesis.expensetracker.services;

import com.thesis.expensetracker.model.Wallet;

public interface WalletService extends CrudService<Wallet, Long> {
    Integer getTotalBalance();
}
