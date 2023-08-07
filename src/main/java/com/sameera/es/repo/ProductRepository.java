package com.sameera.es.repo;

import com.sameera.es.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, Integer> {
    Page<Product> findByName(String name, Pageable pageable);
    Page<Product> findByNameOrKeyword(String name, String keyword, Pageable pageable);

}

