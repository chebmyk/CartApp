package com.svtila.testexample.service.impl;

import com.svtila.testexample.domain.Cart;
import com.svtila.testexample.domain.Product;
import com.svtila.testexample.domain.Promotion;
import com.svtila.testexample.domain.discount.BuyXGetFree;
import com.svtila.testexample.domain.discount.Discount;
import com.svtila.testexample.domain.discount.FlatPercentDiscount;
import com.svtila.testexample.domain.discount.QtyBasePriceOverride;
import com.svtila.testexample.exceptions.NotFoundException;
import com.svtila.testexample.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private Set<Product> products = new HashSet<>();

    @PostConstruct
    void init(){

        Discount buyXgetY = new BuyXGetFree(2, 1);
        Discount QtyPrcOvrrd = new QtyBasePriceOverride(2, 1799.0);
        Discount percent = new FlatPercentDiscount(10);

        Promotion promotionBuyXgetY = new Promotion("1", buyXgetY);
        Promotion promotionQtyPrcOvrrd = new Promotion("2", QtyPrcOvrrd);
        Promotion promotionPercent = new Promotion("3", percent);

        products.add(new Product("1","Fries",100.0, Collections.emptySet()));
        products.add(new Product("2","Salad",200.0, Collections.singleton(promotionPercent)));
        products.add(new Product("3","Burger",500.0, Collections.singleton(promotionBuyXgetY)));
        products.add(new Product("4","Pizza",1000.0, Collections.singleton(promotionQtyPrcOvrrd)));
    }

    @Override
    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public Product getProductById(String id) {
        Optional<Product> result = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        return result.orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @Override
    public boolean addProduct(Product product) {
        return products.add(product);
    }

    @Override
    public boolean removeProduct(String id) {
        Optional<Product> prd = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        return products.remove(prd.orElse(null));
    }

    @Override
    public boolean addPromotion(String prodId, Promotion promotion) {
        Optional<Product> result = products.stream().filter(p -> p.getId().equals(prodId)).findFirst();
        Product  product = result.orElseThrow(() -> new NotFoundException("Product not found"));
        return product.getPromotions().add(promotion);
    }
}
