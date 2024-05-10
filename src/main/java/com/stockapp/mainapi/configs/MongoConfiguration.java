package com.stockapp.mainapi.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
public class MongoConfiguration {
    private final MappingMongoConverter mappingMongoConverter;

    public MongoConfiguration(MappingMongoConverter mappingMongoConverter) {
        this.mappingMongoConverter = mappingMongoConverter;
    }

    @PostConstruct
    public void setUpMongoEscapeCharacterConversion() {
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }

}
