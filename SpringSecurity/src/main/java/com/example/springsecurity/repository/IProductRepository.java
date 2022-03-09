package com.example.springsecurity.repository;

import com.example.springsecurity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {
    Iterable<Product> findProductByNameContaining(String name);
    Iterable<Product> findAllByCategory_Id(Long id);
}
