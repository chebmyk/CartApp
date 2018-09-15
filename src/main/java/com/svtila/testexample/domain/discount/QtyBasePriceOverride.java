package com.svtila.testexample.domain.discount;

import com.svtila.testexample.domain.PromotionType;
import lombok.Data;

import java.io.Serializable;

@Data
public class QtyBasePriceOverride implements Discount, Serializable {
    PromotionType type = PromotionType.QTY_BASED_PRICE_OVERRIDE;
    private long required_qty;
    private double price;

    public QtyBasePriceOverride(long required_qty, double price) {
        this.required_qty = required_qty;
        this.price = price;
    }

    @Override
    public Double applyDiscount(Double price, Long count) {
        long fullPriceCount = count % required_qty;
        long discountCount = count / required_qty;
        return fullPriceCount * price + discountCount * this.price;
    }


}
