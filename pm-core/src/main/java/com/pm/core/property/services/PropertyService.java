package com.pm.core.property.services;

import com.pm.auth.jwt.model.User;
import com.pm.core.property.model.Property;
import com.pm.core.property.model.SearchObject;
import com.pm.core.property.repository.PropertyRepository;
import com.pm.core.property.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public Property createProperty (Property property) throws Exception {
        Property prop = new Property();
        prop.setTitle(property.getTitle());
        prop.setType(property.getType());
        prop.setBedrooms(property.getBedrooms());
        prop.setCreateDate(LocalDateTime.now());
        prop.setExpiryDate(null);

        User user = userRepository.findByUsername("agent");
        property.setUser(user);

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
