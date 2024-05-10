package com.stockapp.mainapi.services.nosql;


import org.springframework.data.mongodb.core.MongoTemplate;

public class IdentifyService {
    private final String IDENTIFY_COLLECTION = "identify";
    public MongoTemplate mongoTemplate;

    public IdentifyService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
