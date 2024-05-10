package com.stockapp.mainapi.models.nosql.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class IdentifyModel {
    @Id
    private String id;

    private String specieId;
    private String image;
    private String name;
    private boolean newSpecie;

    public String getImagePath() {
        if(image == null || id == null) return null;
        return "/uploads/nosql/" + specieId + "/" + image;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNewSpecie() {
        return newSpecie;
    }

    public void setNewSpecie(boolean newSpecie) {
        this.newSpecie = newSpecie;
    }
}
