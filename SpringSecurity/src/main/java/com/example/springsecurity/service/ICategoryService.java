package com.example.springsecurity.service;

import com.example.springsecurity.model.Category;

import java.util.Optional;

public interface ICategoryService {
    Iterable<Category> findAll();

    Optional<Category> findById(Long id);

    Category save(Category category);

    void remove(Long id);
}
