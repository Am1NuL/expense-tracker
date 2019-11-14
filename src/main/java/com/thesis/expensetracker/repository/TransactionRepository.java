package com.thesis.expensetracker.repository;

import com.thesis.expensetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transactions WHERE wallet_id = ?", nativeQuery = true)
    List<Transaction> findAllByWalletId(Long walletId);
}
