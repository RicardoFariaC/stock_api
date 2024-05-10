package com.stockapp.mainapi.models.sql.repositories;

import com.stockapp.mainapi.models.sql.entitites.SpeciesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<SpeciesModel, Integer> {
    SpeciesModel findByName(String name);
    SpeciesModel findById(int id);
    SpeciesModel findBySpecie(String specie);
}
