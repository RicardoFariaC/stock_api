package com.stockapp.mainapi.models.sql.dto;

import java.io.Serializable;

public class SpecieRegistrationDTO implements Serializable {
    private String specie;
    private String name;
    private String description;
    private String registered;
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegisteredBy() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
