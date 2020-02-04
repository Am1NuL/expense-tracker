package com.thesis.expensetracker.services;

import com.thesis.expensetracker.exceptions.NotFoundException;
import com.thesis.expensetracker.model.Category;
import com.thesis.expensetracker.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public DefaultCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categoryRepository.findAll());
    }

    @Override
    public Category findById(Long id) {
        Objects.requireNonNull(id, "Category id is required!");
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    String message = "Category not found. ID: " + id;
                    log.error(message);
                    throw new NotFoundException(message);
                });
    }

    @Override
    public Category save(Category object) {
        return categoryRepository.save(object);
    }

    @Override
    public void delete(Category object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }
}
