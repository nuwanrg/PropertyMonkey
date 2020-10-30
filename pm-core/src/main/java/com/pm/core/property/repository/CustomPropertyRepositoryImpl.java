package com.pm.core.property.repository;

import com.pm.core.property.model.Property;
import com.pm.core.property.model.SearchObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Query.query;

public class CustomPropertyRepositoryImpl implements CustomPropertyRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public CustomPropertyRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Property> search(SearchObject searchObject) {
        Query query= new Query();
        List<Property> properties= new ArrayList<Property>();

        //category
        Criteria categoryCriteria = new Criteria();
        categoryCriteria = Criteria.where( "category").is( searchObject.getCategory());
        query.addCriteria( categoryCriteria );

        //Bedroom criteria
        if ( searchObject.getBedrooms().length > 0) {
            Criteria bedroomCriteria = new Criteria();
            bedroomCriteria = Criteria.where( "bedrooms").in( Arrays.stream(searchObject.getBedrooms()).boxed().toArray(Integer[]::new) );//new  ArrayList(Arrays.asList(searchObject.getBedrooms()))
            query.addCriteria( bedroomCriteria );
        }

        //prop type criteria
        if (searchObject.getTypes().length > 0 ) {
            Criteria typeCriteria = new Criteria();
            typeCriteria = Criteria.where("type").in(Arrays.stream(searchObject.getTypes()).collect(Collectors.toList()));
            query.addCriteria(typeCriteria);
        }

        //locations
        if (searchObject.getLocations().length > 0 ) {
            Criteria criteria = new Criteria();
            criteria = Criteria.where("location").in(Arrays.stream(searchObject.getLocations()).collect(Collectors.toList()));
            query.addCriteria(criteria);
        }

        //min price
        if (searchObject.getMinprice() != 0.0 ) {
            Criteria criteria = new Criteria();
            criteria = Criteria.where("price").gte(searchObject.getMinprice());
            query.addCriteria(criteria);
        }

        //max price
        if (searchObject.getMaxprice() != 0.0 && searchObject.getMaxprice() > searchObject.getMinprice()  ) {
            Criteria criteria = new Criteria();
            criteria = Criteria.where("price").lte(searchObject.getMaxprice());
            query.addCriteria(criteria);
        }


        properties = mongoTemplate.find(query, Property.class);

        return properties;
    }
}
