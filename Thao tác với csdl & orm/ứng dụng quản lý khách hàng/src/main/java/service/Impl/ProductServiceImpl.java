package service.Impl;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IProductRepository;
import service.IProductService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    private List<Product> products = new ArrayList<>();
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ArrayList<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }

    @Override
    public Product getProduct(int id) {
        return productRepository.findProductById(id);
    }

}