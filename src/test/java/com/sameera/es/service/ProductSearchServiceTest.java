package com.sameera.es.service;

import com.sameera.es.EsApplication;
import com.sameera.es.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class ProductSearchServiceTest {

    @Autowired
    private ProductSearchService productSearchService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void setUp() {
        esTemplate.deleteIndex(Product.class);
        esTemplate.createIndex(Product.class);
        esTemplate.putMapping(Product.class);
        esTemplate.refresh(Product.class);
    }

    @Test
    public void save() {
        Product product = new Product(1001, "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017", 20.0);
        Product testProduct = productSearchService.save(product);

        assertNotNull(testProduct.getId());
        assertEquals(testProduct.getName(), product.getName());
        assertEquals(testProduct.getKeyword(), product.getKeyword());
        assertEquals(testProduct.getDescription(), product.getDescription());
        assertEquals(testProduct.getPrice(), product.getPrice(), 0.0001);
    }

    @Test
    public void delete() {
        Product product = new Product(1001, "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017", 20.0);
        productSearchService.save(product);
        productSearchService.delete(product);
        Product testProduct = productSearchService.findOne(product.getId());
        assertNull(testProduct);
    }

    @Test
    public void findOne() {
        Product product = new Product(1001, "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017", 20.0);
        productSearchService.save(product);

        Product testProduct = productSearchService.findOne(product.getId());

        assertNotNull(testProduct.getId());
        assertEquals(testProduct.getName(), product.getName());
        assertEquals(testProduct.getKeyword(), product.getKeyword());
        assertEquals(testProduct.getDescription(), product.getDescription());
        assertEquals(testProduct.getPrice(), product.getPrice(), 0.0001);
    }


    @Test
    public void findByProductName() {
        Product product = new Product(1001, "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017", 20.0);
        productSearchService.save(product);

        Page<Product> byTitle = productSearchService.findByProductName(product.getName(), new PageRequest(0, 10));
        assertThat(byTitle.getTotalElements(), is(1L));
    }
}