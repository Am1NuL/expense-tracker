package com.thesis.expensetracker.bootstrap;

import com.thesis.expensetracker.model.*;
import com.thesis.expensetracker.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final TransactionService transactionService;
    private final CategoryService categoryService;
    private final WalletService walletService;
    private final BudgetService budgetService;
    private final AccountService accountService;

    public DataLoader(TransactionService transactionService, CategoryService categoryService,
                      WalletService walletService, BudgetService budgetService, AccountService accountService) {
        this.transactionService = transactionService;
        this.categoryService = categoryService;
        this.walletService = walletService;
        this.budgetService = budgetService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(this.accountService.findAll().size() == 0) {
            loadData();
        }
    }

    private void loadData() {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        log.debug("Initializing accounts...");
        Account testAccount = new Account();
        testAccount.setDob(LocalDate.now());
        testAccount.setEmail("test@gmail.com");
        testAccount.setFirstName("TEST");
        testAccount.setLastName("ALSO TEST");
        testAccount.setPassword(passwordEncoder.encode("123456"));
        accountService.save(testAccount);

        log.debug("Initializing wallets...");
        Wallet testWallet = new Wallet();
        testWallet.setTotalWealth(255);
        testWallet.setName("Bills");
        testAccount.addWallet(testWallet);
        walletService.save(testWallet);

        Wallet testWallet2 = new Wallet();
        testWallet2.setTotalWealth(1500);
        testWallet2.setName("Personal");
        testAccount.addWallet(testWallet2);
        walletService.save(testWallet2);

        log.debug("Initializing categories...");
        Category gifts = new Category();
        gifts.setAccount(testAccount);
        gifts.setName("Gifts");
        gifts.setType(TransactionType.INCOME);
        categoryService.save(gifts);

        Category bills = new Category();
        bills.setAccount(testAccount);
        bills.setName("Bills");
        bills.setType(TransactionType.EXPENSE);
        categoryService.save(bills);

        Category groceries = new Category();
        groceries.setAccount(testAccount);
        groceries.setName("Groceries");
        groceries.setType(TransactionType.EXPENSE);
        categoryService.save(groceries);

        Category shopping = new Category();
        shopping.setAccount(testAccount);
        shopping.setName("Shopping");
        shopping.setType(TransactionType.EXPENSE);
        categoryService.save(shopping);

        Category salary = new Category();
        salary.setAccount(testAccount);
        salary.setName("Salary");
        salary.setType(TransactionType.INCOME);
        categoryService.save(salary);

        log.debug("Initializing transactions...");
        Transaction income = new Income();
        income.setAmount(50);
        income.setCategory(gifts);
        income.setComment("Ivan loan back");
        income.setDate(LocalDate.now());
        testWallet2.addTransaction(income);
        transactionService.save(income);

        Transaction expense = new Expense();
        expense.setAmount(200);
        expense.setCategory(bills);
        expense.setComment("Water bill for January");
        expense.setDate(LocalDate.now());
        testWallet.addTransaction(expense);
        transactionService.save(expense);

        Transaction income2 = new Expense();
        income2.setAmount(1500);
        income2.setCategory(salary);
        income2.setComment("Salary for January");
        income2.setDate(LocalDate.now());
        testWallet.addTransaction(income2);
        transactionService.save(income2);

        Transaction expense2 = new Expense();
        expense2.setAmount(70);
        expense2.setCategory(shopping);
        expense2.setComment("new pants");
        expense2.setDate(LocalDate.now());
        testWallet.addTransaction(expense2);
        transactionService.save(expense2);

        Transaction expense3 = new Expense();
        expense3.setAmount(100);
        expense3.setCategory(groceries);
        expense3.setComment("Billa shopping");
        expense3.setDate(LocalDate.now());
        testWallet.addTransaction(expense3);
        transactionService.save(expense3);

        log.debug("Initializing budgets...");
        Budget budget = new Budget();
        budget.setAmount(600);
        budget.setCategory(bills);
        budget.setName("Personal");
        budget.setStartDate(LocalDate.of(2020, 1, 11));
        budget.setEndDate(LocalDate.of(2020, 1, 20));
        budget.setWallet(testWallet);
        budget.setRecurrence(RecurrenceType.MONTHLY);
        budgetService.save(budget);

        Budget budget2 = new Budget();
        budget2.setAmount(400);
        budget2.setCategory(bills);
        budget2.setName("Bills");
        budget2.setStartDate(LocalDate.of(2020, 1, 11));
        budget2.setEndDate(LocalDate.of(2020, 1, 20));
        budget2.setWallet(testWallet);
        budget2.setRecurrence(RecurrenceType.MONTHLY);
        budgetService.save(budget2);

        Budget budget3 = new Budget();
        budget3.setAmount(350);
        budget3.setCategory(groceries);
        budget3.setName("Groceries");
        budget3.setStartDate(LocalDate.of(2020, 1, 11));
        budget3.setEndDate(LocalDate.of(2020, 1, 20));
        budget3.setWallet(testWallet2);
        budget3.setRecurrence(RecurrenceType.MONTHLY);
        budgetService.save(budget3);
    }
}
