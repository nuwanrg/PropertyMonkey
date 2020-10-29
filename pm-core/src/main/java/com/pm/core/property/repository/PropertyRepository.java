package com.pm.core.property.repository;

import com.pm.core.property.model.Property;
import com.pm.core.property.model.SearchObject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PropertyRepository extends MongoRepository<Property , String> , CustomPropertyRepository {
    @Override
    public List<Property> search(SearchObject searchObject);
}
