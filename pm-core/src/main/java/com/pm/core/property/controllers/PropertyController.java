package com.pm.core.property.controllers;

import com.pm.core.property.model.Property;
import com.pm.core.property.model.SearchObject;
import com.pm.core.property.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/create")
    @ResponseBody
    public Property createProperty(@RequestBody Property property)throws Exception{
        return propertyService.createProperty(property);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id ) throws Exception{
        propertyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Property> update(@RequestBody Property property) throws Exception{
        return new ResponseEntity<>(propertyService.update(property), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Property>> findAll() throws Exception{
        return new ResponseEntity<>(propertyService.findAll() , HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Property>> search(@RequestBody SearchObject criteria) throws Exception{
       return new ResponseEntity<>(propertyService.search(criteria) , HttpStatus.OK);
    }

}
