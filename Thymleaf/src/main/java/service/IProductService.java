package service;

import model.Product;
import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
    Product findById(int id);
    void remove(int id);
    void update(int id, Product product);
}
