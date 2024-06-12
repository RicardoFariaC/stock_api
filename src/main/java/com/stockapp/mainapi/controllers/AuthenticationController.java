package com.stockapp.mainapi.controllers;

import com.stockapp.mainapi.models.sql.dto.UserLoginDTO;
import com.stockapp.mainapi.models.sql.dto.UserRegistrationDTO;
import com.stockapp.mainapi.models.sql.dto.UserResponseDTO;
import com.stockapp.mainapi.models.sql.entitites.UserModel;
import com.stockapp.mainapi.models.sql.repositories.UserRepository;
import com.stockapp.mainapi.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid UserLoginDTO data) {
        var authToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((UserModel)auth.getPrincipal());

        return ResponseEntity.ok(new UserResponseDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> register(@RequestBody UserRegistrationDTO user) {
        if (this.userRepository.findByUsername(user.username()) != null) return ResponseEntity.badRequest().build();

        String encryptedPass = new BCryptPasswordEncoder().encode(user.password());
        UserModel newUser = new UserModel(user.username(), user.email(), encryptedPass, Set.of());

        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}