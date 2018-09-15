package com.svtila.testexample.domain.discount;

import com.svtila.testexample.domain.PromotionType;
import lombok.Data;

import java.io.Serializable;

@Data
public class BuyXGetFree  implements Discount, Serializable {
    PromotionType type = PromotionType.BUY_X_GET_Y_FREE;
    private long required_qty;
    private long free_qty;

    public BuyXGetFree(long required_qty, long free_qty) {
        this.required_qty = required_qty;
        this.free_qty = free_qty;
    }

    @Override
    public Double applyDiscount(Double price, Long count) {
        long discountCount = (count / required_qty) * free_qty;
        return price * (count - discountCount);
    }
}
