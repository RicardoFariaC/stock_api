package com.stockapp.mainapi.services.sql;

import com.stockapp.mainapi.models.sql.dto.UserRegistrationDTO;
import com.stockapp.mainapi.models.sql.entitites.UserModel;
import com.stockapp.mainapi.models.sql.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCrypt;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCrypt) {
        this.userRepository = userRepository;
        this.bCrypt = bCrypt;
    }

    public UserModel findById(int id) throws Exception {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro durante a procura por um usuário... \n" + e.getMessage());
        }
    }

    public UserModel findByUsername(String username) throws Exception {
        try {
            return userRepository.findByUsername(username);
        } catch (Exception e) {
            throw new Exception("Ocorreu um erro durante a procura por um usuário pelo seu nome... \n" + e.getMessage());
        }
    }
}
