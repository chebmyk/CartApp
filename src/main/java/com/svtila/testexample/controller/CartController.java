package com.svtila.testexample.controller;

import com.svtila.testexample.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
public class CartController {

    private BuyService buyService;

    @Autowired
    public CartController(BuyService buyService) {
        this.buyService = buyService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity showCart() {
        return ResponseEntity.ok(buyService.showCart());
    }

    @PostMapping(value = "/product/{prodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addProductToCart(@PathVariable String prodId) {
        buyService.addProduct(prodId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/product/{prodId}")
    public ResponseEntity deleteProductFromCart(@PathVariable String prodId) {
        boolean result = buyService.removeProductFromCart(prodId);
        if (result) {
            return ResponseEntity.ok("{\"result\": \"Product with id[" + prodId + "] was successfully removed\"}");
        } else {
            return ResponseEntity.ok("{\"result\": \"Product with id[" + prodId + "] does not exist\"}");
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity submit() {
        return ResponseEntity.ok(buyService.submit());
    }

}
