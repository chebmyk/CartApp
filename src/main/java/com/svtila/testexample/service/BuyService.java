package com.svtila.testexample.service;

import com.svtila.testexample.domain.Cart;
import com.svtila.testexample.domain.Product;

import java.util.List;

public interface BuyService {
    Cart showCart();
    boolean addProduct(String productId);
    boolean removeProductFromCart(String productId);
    Cart submit();
}
