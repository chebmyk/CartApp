package com.svtila.testexample.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class Cart {
    //List<Product> products = new ArrayList<>();
    Map<Product,Long> products = new LinkedHashMap<>();
    Double RawTotal;
    Double TotalPromos;
    Double TotalPayable;
}
