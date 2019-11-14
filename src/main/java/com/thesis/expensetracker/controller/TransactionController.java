package com.thesis.expensetracker.controller;

import com.thesis.expensetracker.model.*;
import com.thesis.expensetracker.repository.AccountRepository;
import com.thesis.expensetracker.repository.CategoryRepository;
import com.thesis.expensetracker.repository.TransactionRepository;
import com.thesis.expensetracker.repository.WalletRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    private TransactionRepository transactionRepository;
    private CategoryRepository categoryRepository;
    private WalletRepository walletRepository;
    private AccountRepository accountRepository;

    public TransactionController(TransactionRepository transactionRepository, CategoryRepository categoryRepository, WalletRepository walletRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.walletRepository = walletRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transactions/{id}")
    public List<Transaction> getTransactionsByWallet(@PathVariable("id") Long id) {
        return transactionRepository.findAllByWalletId(id);
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/wallets")
    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    @RequestMapping(value = "/addWallet", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addWallet(@RequestBody Wallet wallet) {
        Account acc = accountRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
        wallet.setAccount(acc);
        walletRepository.save(wallet);
    }

    @RequestMapping(value = "/addTransaction", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTransaction(@RequestBody Transaction transaction) {
        Wallet wallet = walletRepository.findById(transaction.getWallet().getId()).get();

        if(TransactionType.EXPENSE.equals(transaction.getCategory().type)) {
            wallet.setTotalWealth(wallet.getTotalWealth() - transaction.getAmount());
        } else {
            wallet.setTotalWealth(wallet.getTotalWealth() + transaction.getAmount());
        }

        transactionRepository.save(transaction);
    }

    @RequestMapping(value = "/updateTransaction", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTransaction(@RequestBody Transaction transaction) {

        Transaction temp = transactionRepository.findById(transaction.getId()).get();

        temp.setCategory(transaction.getCategory());
        temp.setAmount(transaction.getAmount());
        temp.setComment(transaction.getComment());
        temp.setDate(transaction.getDate());
        temp.setWallet(transaction.getWallet());

        transactionRepository.save(temp);
    }
}
