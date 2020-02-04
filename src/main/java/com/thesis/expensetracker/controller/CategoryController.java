package com.thesis.expensetracker.controller;

import com.thesis.expensetracker.model.Category;
import com.thesis.expensetracker.model.Wallet;
import com.thesis.expensetracker.repository.WalletRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.thesis.expensetracker.services.CategoryService;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    private WalletRepository walletRepository;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }
}

