package com.svtila.testexample.domain.discount;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QtyBasePriceOverrideTest {

    private QtyBasePriceOverride disc1 = new QtyBasePriceOverride(2,1000.0);

    @Test
    public void checkDiscount() {
        double actual = disc1.applyDiscount(1500.0,3L);
        assertEquals(2500.0, actual,0.0);
        actual = disc1.applyDiscount(1500.0,2L);
        assertEquals(1000.0, actual,0.0);
        actual = disc1.applyDiscount(1500.0,1L);
        assertEquals(1500.0, actual,0.0);
    }

}