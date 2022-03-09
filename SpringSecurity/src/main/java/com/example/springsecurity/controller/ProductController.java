package com.example.springsecurity.controller;

import com.example.springsecurity.model.Product;
import com.example.springsecurity.service.ICategoryService;
import com.example.springsecurity.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping()
    public ResponseEntity<Iterable<Product>> findAllProducts() {
        Iterable<Product> products = iProductService.findAll();
        if(!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> productOptional = iProductService.findById(id);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/admin")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(iProductService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(productOptional.get().getId());
        return new ResponseEntity<>(iProductService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = iProductService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iProductService.remove(id);
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Iterable<Product>> findAllProductsByCategory(@PathVariable Long id) {
        Iterable<Product> products = iProductService.findAllByCategory(id);
        if(!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> searchProductsByName(String name){
        Iterable<Product> products = iProductService.findProductByNameContaining(name);
        if(!products.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

}
