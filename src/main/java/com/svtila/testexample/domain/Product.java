package com.svtila.testexample.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Set;

@Data
@EqualsAndHashCode
public class Product implements Serializable {

    public Product(String id, String name, Double price, Set<Promotion> promotions) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.promotions = promotions;
    }

    String id;
    String name;
    Double price;
    Set<Promotion> promotions;
}
