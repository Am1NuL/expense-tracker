package com.thesis.expensetracker.repository;

import com.thesis.expensetracker.model.Budget;
import com.thesis.expensetracker.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @Query(value = "SELECT * FROM budgets WHERE wallet_id = ?", nativeQuery = true)
    List<Budget> findByWalletId(Long id);
}
