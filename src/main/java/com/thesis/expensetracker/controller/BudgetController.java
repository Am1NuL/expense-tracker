package com.thesis.expensetracker.controller;

import com.thesis.expensetracker.model.Budget;
import com.thesis.expensetracker.services.BudgetService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BudgetController {

    private BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/budgets")
    public List<Budget> getBudgets() {
        return budgetService.findAll();
    }

    @GetMapping("/wallets/{id}/budgets")
    public List<Budget> getBudgetsByWallet(@PathVariable Long id) {
        return budgetService.findByWalletId(id);
    }

    @GetMapping("/budgets/{id}")
    public Budget getBudgetById(@PathVariable Long id) {
        return budgetService.findById(id);
    }

    @PostMapping("/budgets")
    public Budget addBudget(@RequestBody Budget budget) {
        return this.budgetService.save(budget);
    }

    @RequestMapping(value = "/budgets", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTransaction(@RequestBody Budget budget) {

        Budget temp = budgetService.findById(budget.getId());

        temp.setName(budget.getName());
        temp.setCategory(budget.getCategory());
        temp.setWallet(budget.getWallet());
        temp.setStartDate(budget.getStartDate());
        temp.setEndDate(budget.getEndDate());
        temp.setAmount(budget.getAmount());


        budgetService.save(temp);
    }
}
