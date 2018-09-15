package com.svtila.testexample.controller;

import com.svtila.testexample.domain.Product;
import com.svtila.testexample.domain.Promotion;
import com.svtila.testexample.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product ));
    }

    @PostMapping(value = "/{id}/promotion", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addPromotionToProduct(@PathVariable String id, @RequestBody Promotion promotion) {
        return ResponseEntity.ok(productService.addPromotion(id, promotion));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        productService.removeProduct(id);
        return ResponseEntity.ok("{\"result\": \"Product with id[" + id + "] was successfully removed\"}");
    }
}
