package com.stockapp.mainapi.models.nosql.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "identify")
public class IdentifyModel {
    @Id
    private String id;
    private int specieId = 0;
    private String image;
    private int identifiedBy;
    private String name;

    public IdentifyModel(String name, String image, int identifiedBy) {
        this.name = name;
        this.identifiedBy = identifiedBy;
        this.image = image;
    }

    public IdentifyModel(int specieId, String name, String image, int identifiedBy) {
        this.specieId = specieId;
        this.image = image;
        this.identifiedBy = identifiedBy;
        this.name = name;
    }

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

    public int getSpecieId() {
        return specieId;
    }

    public void setSpecieId(int specieId) {
        this.specieId = specieId;
    }

    public String getImage() {
        return image;
    }

    public int getIdentifiedBy() {
        return identifiedBy;
    }

    public void setIdentifiedBy(int identifiedBy) {
        this.identifiedBy = identifiedBy;
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

}
