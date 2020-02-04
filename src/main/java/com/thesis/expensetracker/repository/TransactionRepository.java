package com.thesis.expensetracker.repository;

import com.thesis.expensetracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM transactions WHERE wallet_id = ?", nativeQuery = true)
    List<Transaction> findAllByWalletId(Long walletId);

    @Query(value = "SELECT SUM(AMOUNT) FROM transactions WHERE wallet_id = :walletId and category_id = :categoryId and date >= :startDate and date <= :endDate", nativeQuery = true)
    Integer getExpenseSum(@Param("walletId") Long walletId,
                      @Param("categoryId") Long categoryId,
                      @Param("startDate") LocalDate startDate,
                      @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT SUM(AMOUNT) FROM transactions WHERE transaction_type = 'E'", nativeQuery = true)
    Integer getTotalExpenses();

    @Query(value = "SELECT SUM(AMOUNT) FROM transactions WHERE transaction_type = 'I'", nativeQuery = true)
    Integer getTotalIncome();
}
