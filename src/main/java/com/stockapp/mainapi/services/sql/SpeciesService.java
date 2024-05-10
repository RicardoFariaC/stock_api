package com.stockapp.mainapi.services.sql;

import com.stockapp.mainapi.models.sql.dto.SpecieRegistrationDTO;
import com.stockapp.mainapi.models.sql.dto.SpecieResponseDTO;
import com.stockapp.mainapi.models.sql.entitites.SpeciesModel;
import com.stockapp.mainapi.models.sql.entitites.UserModel;
import com.stockapp.mainapi.models.sql.repositories.SpeciesRepository;
import com.stockapp.mainapi.models.sql.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpeciesService {
    private final SpeciesRepository speciesRepository;
    private final UserRepository userRepository;

    public SpeciesService(SpeciesRepository speciesRepository, UserRepository userRepository) {
        this.speciesRepository = speciesRepository;
        this.userRepository = userRepository;
    }

    public SpecieResponseDTO findById(int id) throws Exception {
        try {
            SpeciesModel specie = this.speciesRepository.findById(id);
            return new SpecieResponseDTO(specie);
        } catch (Exception e) {
            throw new Exception("Species not found.\n" + e.getMessage());
        }
    }

    public SpecieResponseDTO findByName(String name) throws Exception {
        try {
            SpeciesModel specie = this.speciesRepository.findByName(name);
            return new SpecieResponseDTO(specie);
        } catch (Exception e) {
            throw new Exception("Species not found.\n" + e.getMessage());
        }
    }

    public List<SpecieResponseDTO> findAll() throws Exception {
        try {
            List<SpeciesModel> species = this.speciesRepository.findAll();
            return species.stream().map(
                    SpecieResponseDTO::new
            ).toList();
        } catch (Exception e) {
            throw new Exception("Species not found.\n" + e.getMessage());
        }
    }

    public void register(SpecieRegistrationDTO dto) throws Exception {
        try {
            UserModel user = userRepository.findById(Integer.parseInt(dto.getRegisteredBy()));
            if(user == null) throw new Exception("User not found");
            this.speciesRepository.save(new SpeciesModel(dto, user));
        } catch (Exception e) {
            throw new Exception("Cannot insert a new specie.\n" + e.getMessage());
        }
    }

}
