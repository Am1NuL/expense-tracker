package com.thesis.expensetracker.services;

import com.thesis.expensetracker.exceptions.NotFoundException;
import com.thesis.expensetracker.model.Budget;
import com.thesis.expensetracker.repository.BudgetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DefaultBudgetService implements BudgetService {

    private final BudgetRepository budgetRepository;

    public DefaultBudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public List<Budget> findAll() {
        return new ArrayList<>(budgetRepository.findAll());
    }

    @Override
    public Budget findById(Long id) {
        Objects.requireNonNull(id, "Budget id is required!");
        return budgetRepository.findById(id)
                .orElseThrow(() -> {
                    String message = "Budget not found. ID: " + id;
                    log.error(message);
                    throw new NotFoundException(message);
                });
    }

    @Override
    public Budget save(Budget object) {
        return budgetRepository.save(object);
    }

    @Override
    public void delete(Budget object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<Budget> findByWalletId(Long id) {
        return budgetRepository.findByWalletId(id);
    }
}
