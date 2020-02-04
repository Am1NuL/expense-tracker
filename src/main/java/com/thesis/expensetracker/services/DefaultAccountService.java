package com.thesis.expensetracker.services;

import com.thesis.expensetracker.exceptions.NotFoundException;
import com.thesis.expensetracker.model.Account;
import com.thesis.expensetracker.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;

    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accountRepository.findAll());
    }

    @Override
    public Account findById(Long id) {
        Objects.requireNonNull(id, "Account id is required!");
        return accountRepository.findById(id)
                .orElseThrow(() -> {
                    String message = "Account not found. ID: " + id;
                    log.error(message);
                    throw new NotFoundException(message);
                });
    }

    @Override
    public Account save(Account object) {
        return accountRepository.save(object);
    }

    @Override
    public void delete(Account object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Account findByEmail(String email) {
        return this.accountRepository.findByEmail(email);
    }
}
