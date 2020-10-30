package com.pm.core.property.model;

import lombok.Data;

import java.lang.reflect.Array;

@Data
public class SearchObject {
    private String category;
    private String[] types;
    private int[] bedrooms;
    private String[] locations;
    private double minprice;
    private double maxprice;

    public SearchObject(){
        types = new String[]{};
        bedrooms = new int[]{};
        locations = new String[]{};

    }
}
