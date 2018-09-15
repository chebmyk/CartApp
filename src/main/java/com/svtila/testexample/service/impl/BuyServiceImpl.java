package com.svtila.testexample.service.impl;

import com.svtila.testexample.domain.Cart;
import com.svtila.testexample.domain.Product;
import com.svtila.testexample.service.BuyService;
import com.svtila.testexample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BuyServiceImpl implements BuyService {

    private ProductService productService;

    @Autowired
    public BuyServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    private Cart cart = new Cart();

    @Override
    public Cart showCart() {
        return cart;
    }

    @Override
    public boolean addProduct(String productId) {
        Product product = productService.getProductById(productId);
        cart.getProducts().merge(product, 1L, (o, n) -> o + n);
        return true;
    }

    @Override
    public boolean removeProductFromCart(String productId) {
        Product product = productService.getProductById(productId);
        if(cart.getProducts().containsKey(product)) {
            Long cnt = cart.getProducts().merge(product, 1L, (o, n) -> o - n);
            if (cnt == 0) {
                cart.getProducts().remove(product);
            }
            return true;
        }
        return false;
    }

    @Override
    public Cart submit() {
        calculateTotal();
        return cart;
    }

    private void calculateTotal() {
        Double totalPrice = 0.0;
        Double discount = 0.0;
        for (Map.Entry<Product, Long> cartItem : cart.getProducts().entrySet()) {
            long cnt = cartItem.getValue();
            double price = cartItem.getKey().getPrice();
            totalPrice += price * cnt;
            discount += cartItem.getKey().getPromotions().stream().mapToDouble(item -> (price * cnt - item.getDiscount().applyDiscount(price, cnt))).sum();
        }
        cart.setRawTotal(totalPrice);
        cart.setTotalPromos(discount);
        cart.setTotalPayable(totalPrice - discount);
    }
}
