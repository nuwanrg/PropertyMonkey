package com.pm.core.property.services;

import com.pm.core.property.model.Property;
import com.pm.core.property.model.SearchObject;
import com.pm.core.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public Property createProperty (Property property) throws Exception {
        return propertyRepository.save(property);
    }

    public void delete (String id) throws Exception {
        propertyRepository.deleteById(id);
    }

    public Property update(Property property) throws Exception {
        return propertyRepository.save(property);
    }

    public List<Property> findAll() throws Exception {
        return propertyRepository.findAll();
    }

    public List<Property> search(SearchObject criteria) throws Exception {
        return propertyRepository.search(criteria);
    }
}
