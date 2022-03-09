package com.example.springsecurity.service;

import com.example.springsecurity.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findProductByNameContaining(String name);
    Iterable<Product> findAllByCategory(Long id);

    Iterable<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void remove(Long id);
}
