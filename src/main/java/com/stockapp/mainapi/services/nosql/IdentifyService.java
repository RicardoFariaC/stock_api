package com.stockapp.mainapi.services.nosql;


import com.stockapp.mainapi.models.nosql.entities.IdentifyModel;
import com.stockapp.mainapi.models.nosql.repositories.IdentifyRepository;
import org.springframework.stereotype.Service;

@Service
public class IdentifyService {
    private final String IDENTIFY_COLLECTION = "identify";
    private final IdentifyRepository identifyRepository;

    public IdentifyService(IdentifyRepository identifyRepository) {
        this.identifyRepository = identifyRepository;
    }

    public void register(IdentifyModel identify) {
        this.identifyRepository.save(identify);
    }

}
