package com.thesis.expensetracker.repository;

import com.thesis.expensetracker.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
