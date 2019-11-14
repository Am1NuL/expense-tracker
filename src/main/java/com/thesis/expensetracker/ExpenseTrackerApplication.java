package com.thesis.expensetracker;

import com.thesis.expensetracker.model.*;
import com.thesis.expensetracker.repository.AccountRepository;
import com.thesis.expensetracker.repository.CategoryRepository;
import com.thesis.expensetracker.repository.TransactionRepository;
import com.thesis.expensetracker.repository.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootApplication
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(AccountRepository accountRepository, CategoryRepository categoryRepository, WalletRepository walletRepository, TransactionRepository transactionRepository) {
		return args -> {

//			Account testAcc = new Account();
//			LocalDate date = LocalDate.now();
//			testAcc.setDob(date);
//
//			testAcc.setEmail("dasdasd");
//			testAcc.setFirstName("aSDASDsad");
//			testAcc.setLastName("dasdasasd");
//
//			accountRepository.save(testAcc);

//			Account acc = accountRepository.findById(1L).get();
//			Category cat = categoryRepository.findById(1L).get();
//			Wallet wallet = walletRepository.findById(1L).get();
//
//			Transaction testTrans = new Expense(20, LocalDate.now(), cat, wallet);
//			transactionRepository.save(testTrans);

//			Category testC = new Category("testC", TransactionType.EXPENSE);
//			categoryRepository.save(testC);
//
//			Wallet testW = new Wallet();
//			testW.setTotalWealth(233);
//			testW.setAccount(acc);
//			walletRepository.save(testW);


		};
	}
}
