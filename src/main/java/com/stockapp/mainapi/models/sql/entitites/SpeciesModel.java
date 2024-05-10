package com.stockapp.mainapi.models.sql.entitites;

import com.stockapp.mainapi.models.sql.dto.SpecieRegistrationDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity(name = "species")
public class SpeciesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotBlank
    @NotNull
    private String image;

    @Column
    @NotBlank
    @NotNull
    private String name;

    @Column
    @NotBlank
    @NotNull
    private String specie;
    @Column
    @NotBlank
    @NotNull
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserModel registered_by;

    @Column
    @NotNull
    private boolean isActive = true;

    @Column
    @CreationTimestamp
    private Date createdAt;

    @Column(nullable = false, updatable = false)
    @UpdateTimestamp
    private Date updatedAt;

    public SpeciesModel() {}

    public SpeciesModel(String name, String specie, String description, UserModel registered_by) {
        this.name = name;
        this.specie = specie;
        this.description = description;
        this.registered_by = registered_by;
    }

    public SpeciesModel(SpecieRegistrationDTO dto, UserModel registered_by) {
        this.name = dto.getName();
        this.specie = dto.getSpecie();
        this.description = dto.getDescription();
        this.image = dto.getPath();
        this.registered_by = registered_by;
    }

    public String getImagePath() {
        if(image == null || id == 0) return null;
        return "/uploads/" + id + "/" + image;
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

    public UserModel getRegisteredBy() {
        return registered_by;
    }

    public void setRegisteredBy(UserModel registered_by) {
        this.registered_by = registered_by;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
