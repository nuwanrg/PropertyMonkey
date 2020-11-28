package com.pm.core.property.services;

import com.pm.common.persistence.model.User;
import com.pm.core.property.dto.PropertyDto;
import com.pm.core.property.exception.PropertyException;
import com.pm.core.property.model.Property;
import com.pm.core.property.model.SearchObject;
import com.pm.core.property.repository.PropertyRepository;

import com.pm.core.property.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Component("propertyService")
public class PropertyService implements IPropertyInterface{
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public Property createProperty (Property property) throws Exception {
        User user = userRepository.findByUsername(property.getUsername());
        if (user != null ) {
            property.setCreateDate(LocalDateTime.now());
            property.setExpiryDate(null);
            property.setUser(user);
            return propertyRepository.save(property);
        } else
        {
            throw new PropertyException("Error in creating");
        }

    }

    public void delete (String id) throws Exception {
        propertyRepository.deleteById(id);
    }

    public Property update(Property property) throws Exception {
        return propertyRepository.save(property);
    }

    public List<Property> findAll() throws Exception {
        List<Property> properties = propertyRepository.findAll();
        return properties;
    }

    public List<Property> search(SearchObject criteria) throws Exception {
        return propertyRepository.search(criteria);
    }
}
