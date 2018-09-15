package com.svtila.testexample.domain.discount;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuyXGetFreeTest {
    private BuyXGetFree disc1 = new BuyXGetFree(3,1);

    @Test
    public void checkDiscount() {
        double actual = disc1.applyDiscount(10.0,3L);
        assertEquals(20.0, actual,0.0);
        actual = disc1.applyDiscount(10.0,2L);
        assertEquals(20.0, actual,0.0);
        actual = disc1.applyDiscount(10.0,1L);
        assertEquals(10.0, actual,0.0);
        actual = disc1.applyDiscount(10.0,4L);
        assertEquals(30.0, actual,0.0);
    }

}