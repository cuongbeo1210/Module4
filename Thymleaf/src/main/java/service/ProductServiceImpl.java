package service;

import model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements IProductService {
    private static final Map<Integer, Product> products;

    static{
        products = new HashMap<>();
        products.put(1, new Product(1, "Iphone 13", 15000000, "Like New"));
        products.put(2, new Product(2, "Samsung S21", 18000000, "New"));
        products.put(3, new Product(3, "Oppo X5", 14000000, "Old"));
        products.put(4, new Product(4, "Nokia M7", 13000000, "99%"));
        products.put(5, new Product(5, "Xiaomi Mi11", 11000000, "100%"));
        products.put(6, new Product(6, "Vivo V70", 12000000, "Seal"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }
}
