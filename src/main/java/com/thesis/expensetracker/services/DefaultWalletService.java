package com.thesis.expensetracker.services;

import com.thesis.expensetracker.exceptions.NotFoundException;
import com.thesis.expensetracker.model.Wallet;
import com.thesis.expensetracker.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DefaultWalletService implements WalletService {

    private final WalletRepository walletRepository;

    public DefaultWalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Wallet> findAll() {
        return new ArrayList<>(walletRepository.findAll());
    }

    @Override
    public Wallet findById(Long id) {
        Objects.requireNonNull(id, "Wallet id is required!");
        return walletRepository.findById(id)
                .orElseThrow(() -> {
                    String message = "Transaction not found. ID: " + id;
                    log.error(message);
                    throw new NotFoundException(message);
                });
    }

    @Override
    public Wallet save(Wallet object) {
        return walletRepository.save(object);
    }

    @Override
    public void delete(Wallet object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Integer getTotalBalance() {
        return walletRepository.getTotalBalance();
    }
}
