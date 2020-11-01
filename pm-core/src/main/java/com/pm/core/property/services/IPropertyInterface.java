package com.pm.core.property.services;

import com.pm.core.property.dto.PropertyDto;
import com.pm.core.property.model.Property;

public interface IPropertyInterface {

    public Property createProperty (Property property) throws Exception;
}
