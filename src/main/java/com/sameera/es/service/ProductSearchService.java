package com.sameera.es.service;

import com.sameera.es.domain.Product;
import com.sameera.es.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductSearchService {
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public Product findOne(Integer id) {
        return productRepository.findOne(id);
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> findByProductName(String name, PageRequest pageRequest) {
        return productRepository.findByName(name, pageRequest);
    }

    public Page<Product> findByNameOrKeyword(String name, String keyword, PageRequest pageRequest) {
        return productRepository.findByNameOrKeyword(name, keyword, pageRequest);
    }
}
