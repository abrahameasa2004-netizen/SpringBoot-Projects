package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Add Product
    public Product addProduct(Product product) {
        return repository.save(product);
    }

    // Get All Products
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Get Product By Id
    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Update Product
    public Product updateProduct(Long id, Product product) {

        Product existing = getProductById(id);

        existing.setProductName(product.getProductName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        existing.setImageUrl(product.getImageUrl());

        return repository.save(existing);
    }

    // Delete Product
    public String deleteProduct(Long id) {

        repository.deleteById(id);

        return "Product Deleted Successfully";
    }

    // Search
    public List<Product> searchProduct(String name) {
        return repository.findByProductNameContainingIgnoreCase(name);
    }

}