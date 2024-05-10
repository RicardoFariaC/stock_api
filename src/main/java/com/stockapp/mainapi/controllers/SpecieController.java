package com.stockapp.mainapi.controllers;

import com.stockapp.mainapi.models.sql.dto.SpecieRegistrationDTO;
import com.stockapp.mainapi.models.sql.dto.SpecieResponseDTO;
import com.stockapp.mainapi.security.UserCanOnlyRegisterTheirOwnSpecie;
import com.stockapp.mainapi.services.sql.SpeciesService;
import com.stockapp.mainapi.utils.FileUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api/species")
public class SpecieController {
    private final SpeciesService speciesService;
    public SpecieController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> getSpecies() {
        try {
            List<SpecieResponseDTO> species = this.speciesService.findAll();
            return new ResponseEntity<>(species, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("message", e.getMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> getSpecieById(@PathVariable("id") int id) {
        try {
            SpecieResponseDTO specie = this.speciesService.findById(id);
            return new ResponseEntity<>(specie, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("message", e.getMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/n/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> getSpecieByName(@PathVariable("name") String name) {
        try {
            SpecieResponseDTO specie = this.speciesService.findByName(name);
            return new ResponseEntity<>(specie, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            HashMap<String, String> hm = new HashMap<>();
            hm.put("message", e.getMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @UserCanOnlyRegisterTheirOwnSpecie
    public ResponseEntity<Object> createSpecie(@ModelAttribute SpecieRegistrationDTO dto, @RequestParam("file")MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        fileName = UUID.randomUUID() + "-" + fileName;
        dto.setPath(fileName);
        HashMap<String, String> hm = new HashMap<>();
        try {
            this.speciesService.register(dto);
            String uploadDir = "./uploads/" + dto.getRegisteredBy();
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            hm.put("message", "specie registered successfully");
            return new ResponseEntity<>(hm, HttpStatus.CREATED);
        } catch (Exception e) {
            hm.put("message", e.getMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
