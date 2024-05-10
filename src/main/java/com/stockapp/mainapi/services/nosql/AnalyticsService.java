package com.stockapp.mainapi.services.nosql;

import com.stockapp.mainapi.models.nosql.entities.AnalyticsModel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    private final String IDENTIFY_COLLECTION = "analytics";
    public MongoTemplate mongoTemplate;

    public AnalyticsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addIdentifiedSpecie(String specieName, boolean newSpecie) {
        AnalyticsModel analyticsModel = new AnalyticsModel();
        analyticsModel.setSpecieName(specieName);
        analyticsModel.setNewSpecie(newSpecie);
        analyticsModel.setSpecieCount(1L);

        mongoTemplate.insert(analyticsModel, IDENTIFY_COLLECTION);
    }
}
