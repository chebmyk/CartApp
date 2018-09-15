package com.svtila.testexample.service;

import com.svtila.testexample.domain.Product;
import com.svtila.testexample.domain.Promotion;
import com.svtila.testexample.exceptions.NotFoundException;

import java.util.List;
import java.util.Set;

public interface ProductService {

    Set<Product> getProducts();
    Product getProductById(String id);
    boolean addProduct(Product product);
    boolean removeProduct(String id);
    boolean addPromotion(String prodId, Promotion promotion);
}
