package com.stockapp.mainapi.controllers;
import com.stockapp.mainapi.models.sql.dto.UserRegistrationDTO;
import com.stockapp.mainapi.models.sql.entitites.UserModel;
import com.stockapp.mainapi.services.sql.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("#id == authentication.principal.getId()")
    public ResponseEntity<Object> getUser(@PathVariable("id") int id) {
        HashMap<String, String> hm = new HashMap<>();
        try {
            UserModel user = this.userService.findById(id);
            hm.put("username", user.getUsername());
            hm.put("message", String.valueOf(user.getId()));
            return new ResponseEntity<>(hm, HttpStatus.ACCEPTED);
        } catch(Exception e) {
            hm.put("message", e.getMessage());
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
