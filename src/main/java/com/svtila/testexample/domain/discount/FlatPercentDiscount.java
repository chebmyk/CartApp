package com.svtila.testexample.domain.discount;

import com.svtila.testexample.domain.PromotionType;
import lombok.Data;

import java.io.Serializable;

@Data
public class FlatPercentDiscount implements Discount, Serializable {
    PromotionType type = PromotionType.FLAT_PERCENT;
    private int percent;

    public FlatPercentDiscount(int percent) {
        this.percent = percent;
    }

    @Override
    public Double applyDiscount(Double price, Long count) {
        return (price * (1 - percent/100.0)) * count;
    }
}
