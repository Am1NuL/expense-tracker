package com.thesis.expensetracker.services;

import com.thesis.expensetracker.model.Budget;

import java.util.List;

public interface BudgetService extends CrudService<Budget, Long> {
    List<Budget> findByWalletId(Long id);
}
