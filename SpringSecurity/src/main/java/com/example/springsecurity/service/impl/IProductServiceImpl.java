package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.Product;
import com.example.springsecurity.repository.IProductRepository;
import com.example.springsecurity.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public Iterable<Product> findProductByNameContaining(String name) {
        return iProductRepository.findProductByNameContaining(name);
    }

    @Override
    public Iterable<Product> findAllByCategory(Long id) {
        return iProductRepository.findAllByCategory_Id(id);
    }

    @Override
    public Iterable<Product> findAll() {
        return iProductRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        iProductRepository.deleteById(id);
    }
}
