package com.pm.core.property.repository;

import com.pm.core.property.model.Property;
import com.pm.core.property.model.SearchObject;

import java.util.List;

public interface CustomPropertyRepository {
    public List<Property> search(SearchObject searchObject);
}
