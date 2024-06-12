package com.stockapp.mainapi.models.nosql.repositories;

import com.stockapp.mainapi.models.nosql.entities.IdentifyModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdentifyRepository extends MongoRepository<IdentifyModel, String> {
}
