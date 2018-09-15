package com.svtila.testexample.domain.discount;

public interface Discount {
    Double applyDiscount(Double price, Long count);
}
