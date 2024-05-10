package com.stockapp.mainapi.models.nosql.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AnalyticsModel {
    @Id
    private String id;

    private String specieId;
    private String specieName;
    private Long specieCount;
    private boolean newSpecie = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecieId() {
        return specieId;
    }

    public void setSpecieId(String specieId) {
        this.specieId = specieId;
    }

    public String getSpecieName() {
        return specieName;
    }

    public void setSpecieName(String specieName) {
        this.specieName = specieName;
    }

    public Long getSpecieCount() {
        return specieCount;
    }

    public void setSpecieCount(Long specieCount) {
        this.specieCount = specieCount;
    }

    public boolean isNewSpecie() {
        return newSpecie;
    }

    public void setNewSpecie(boolean newSpecie) {
        this.newSpecie = newSpecie;
    }
}
