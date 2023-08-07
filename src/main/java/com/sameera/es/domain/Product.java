package com.sameera.es.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "productindex", type = "products")
public class Product {

    @Id
    private Integer id;

    @Field(type = FieldType.String)
    private String name;

    @Field(type = FieldType.String)
    //@Field(type = FieldType.String, analyzer = "ik_max_word")
    private String keyword;

    @Field(type = FieldType.String)
    private String description;

    @Field(type = FieldType.Double)
    private double price;

    public Product() {

    }
    public Product(Integer id, String name, String keyword, String description, double price) {
        this.id = id;
        this.name = name;
        this.keyword = keyword;
        this.description = description;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
