package com.svtila.testexample.domain;

import com.svtila.testexample.domain.discount.Discount;
import lombok.Data;

import java.io.Serializable;

@Data
public class Promotion implements Serializable {
    String id;
    Discount discount;

    public Promotion(String id, Discount discount) {
        this.id = id;
        this.discount = discount;
    }

    Double calculate(Double price, Long count){
        return discount.applyDiscount(price, count);
    }
}
