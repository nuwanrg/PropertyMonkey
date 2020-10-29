package com.pm.core.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoClientConfig  {

    public @Bean
    MongoClient mongoClient() {
        MongoClient mongoClient = MongoClients.create("mongodb://myUserAdmin:monkey123@54.221.159.129:27017");
        //mongo.url=mongodb://<user>:<password>@<host>:27017
        return mongoClient;
    }

    public @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "pm");
    }
}
