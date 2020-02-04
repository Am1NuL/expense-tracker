package com.thesis.expensetracker.controller;

import com.thesis.expensetracker.model.*;
import com.thesis.expensetracker.repository.AccountRepository;
import com.thesis.expensetracker.repository.TransactionRepository;
import com.thesis.expensetracker.repository.WalletRepository;
import com.thesis.expensetracker.services.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TransactionController {

    private TransactionRepository transactionRepository;
    private WalletRepository walletRepository;
    private final AccountRepository accountRepository;
    private BudgetService budgetService;

    public TransactionController(TransactionRepository transactionRepository, WalletRepository walletRepository,
                                 AccountRepository accountRepository, BudgetService budgetService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.accountRepository = accountRepository;
        this.budgetService = budgetService;
    }

    @GetMapping(produces = "application/json")
    @RequestMapping({"/login/validate"})
    public Account validateLogin() {
        return new Account();
    }

    @GetMapping("/budgets/{id}/expenses")
    public Integer getExpenseSum(@PathVariable Long id) {
        Budget budget = budgetService.findById(id);

        return transactionRepository.getExpenseSum(budget.getWallet().getId(), budget.getCategory().getId(),
                budget.getStartDate(), budget.getEndDate());
    }

    @PostMapping("/wallets/create")
    public Wallet addWallet(@RequestBody Wallet wallet) {
//        Account acc = accountService.findById(1L);
//        acc.addWallet(wallet);
        return walletRepository.save(wallet);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/wallets/{id}/transactions")
    public List<Transaction> getTransactionsByWallet(@PathVariable("id") Long id) {
        return transactionRepository.findAllByWalletId(id);
    }

    @GetMapping("/total-expenses")
    public Integer getTotalExpenses() {
        return transactionRepository.getTotalExpenses();
    }

    @GetMapping("/total-income")
    public Integer getTotalIncome() {
        return transactionRepository.getTotalIncome();
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTransaction(@RequestBody Transaction transaction) {
        Wallet wallet = walletRepository.findById(transaction.getWallet().getId()).get();

        if (TransactionType.EXPENSE.equals(transaction.getCategory().type)) {
            wallet.setTotalWealth(wallet.getTotalWealth() - transaction.getAmount());
        } else {
            wallet.setTotalWealth(wallet.getTotalWealth() + transaction.getAmount());
        }

        transactionRepository.save(transaction);
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
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
