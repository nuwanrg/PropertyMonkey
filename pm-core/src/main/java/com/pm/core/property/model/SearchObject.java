package com.pm.core.property.model;

import lombok.Data;

@Data
public class SearchObject {
    private String[] types;
    private int[] bedrooms;
    private String[] locations;
}
