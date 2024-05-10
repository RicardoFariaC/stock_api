package com.stockapp.mainapi.models.sql.dto;

import com.stockapp.mainapi.models.sql.entitites.SpeciesModel;

public class SpecieResponseDTO {
    private int id;
    private String image;
    private String name;
    private String specie;
    private String description;
    private SpecieRegisteredByUserDTO registered_by;

    public SpecieResponseDTO(SpeciesModel specie) {
        this.id = specie.getId();
        this.image = specie.getImage();
        this.name = specie.getName();
        this.specie = specie.getSpecie();
        this.description = specie.getDescription();
        this.registered_by = new SpecieRegisteredByUserDTO(specie.getRegisteredBy().getId(), specie.getRegisteredBy().getUsername());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public SpecieRegisteredByUserDTO getRegistered_by() {
        return registered_by;
    }

    public void setRegistered_by(SpecieRegisteredByUserDTO registered_by) {
        this.registered_by = registered_by;
    }
}
