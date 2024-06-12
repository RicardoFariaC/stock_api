package com.stockapp.mainapi.controllers;

import com.stockapp.mainapi.models.nosql.dto.IdentifiedSpecieResponseWhenRegisteredDTO;
import com.stockapp.mainapi.models.nosql.dto.IdentifySpecieDTO;
import com.stockapp.mainapi.models.nosql.entities.IdentifyModel;
import com.stockapp.mainapi.models.sql.dto.SpecieRegistrationDTO;
import com.stockapp.mainapi.models.sql.dto.SpecieResponseDTO;
import com.stockapp.mainapi.models.sql.dto.SpecieResponseWhenCreatedDTO;
import com.stockapp.mainapi.models.sql.entitites.UserModel;
import com.stockapp.mainapi.security.UserCanOnlyRegisterTheirOwnSpecie;
import com.stockapp.mainapi.services.nosql.IdentifyService;
import com.stockapp.mainapi.services.sql.SpeciesService;
import com.stockapp.mainapi.services.sql.UserService;
import com.stockapp.mainapi.utils.FileUploadUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/specie")
public class SpecieController {
    private final SpeciesService speciesService;
    private final UserService userService;
    private final IdentifyService identifyService;
    public SpecieController(SpeciesService speciesService, UserService userService, IdentifyService identifyService) {
        this.speciesService = speciesService;
        this.userService = userService;
        this.identifyService = identifyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> getSpecies() {
        try {
            List<SpecieResponseDTO> species = this.speciesService.findAll();
            return ResponseEntity.ok(species);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getLocalizedMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> getSpecieById(@PathVariable("id") int id) {
        try {
            SpecieResponseDTO specie = this.speciesService.findById(id);
            return ResponseEntity.ok(specie);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/n/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Object> getSpecieByName(@PathVariable("name") String name) {
        try {
            SpecieResponseDTO specie = this.speciesService.findByName(name);
            return ResponseEntity.ok(specie);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @UserCanOnlyRegisterTheirOwnSpecie
    public ResponseEntity<Object> createSpecie(@ModelAttribute SpecieRegistrationDTO dto, @RequestParam("file")MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        fileName = UUID.randomUUID() + "-" + fileName;
        dto.setPath(fileName);
        try {
            this.speciesService.register(dto);
            String uploadDir = "./uploads/" + dto.getRegisteredBy();
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            return ResponseEntity.ok(new SpecieResponseWhenCreatedDTO(dto.getName(), dto.getSpecie(), dto.getRegisteredBy()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/identify")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> registerIdentifiedSpecie(@ModelAttribute IdentifySpecieDTO dto, @AuthenticationPrincipal UserDetails currentUser, @RequestParam("file")MultipartFile file) throws Exception {
        SpecieResponseDTO specieRes = null;
        try {
            specieRes = this.speciesService.findByName(dto.getName());
        } catch (Exception ignored) {}
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        fileName = UUID.randomUUID()+"-"+fileName;
        dto.setPath(fileName);
        try {
            UserModel user = userService.findByUsername(currentUser.getUsername());
            String name = dto.getName().toLowerCase();
            if(specieRes == null) {
                IdentifyModel identify = new IdentifyModel(name, dto.getPath(), user.getId());
                String uploadDir = "./uploads/species/" + name;
                FileUploadUtil.saveFile(uploadDir, fileName, file);
                this.identifyService.register(identify);
                return ResponseEntity.ok(new IdentifiedSpecieResponseWhenRegisteredDTO(name, user.getId()));
            }

            IdentifyModel identify = new IdentifyModel(specieRes.getId(), name, dto.getPath(), user.getId());
            String uploadDir = "./uploads/species/" + specieRes.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, file);
            this.identifyService.register(identify);
            return ResponseEntity.ok(new IdentifiedSpecieResponseWhenRegisteredDTO(name, user.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
