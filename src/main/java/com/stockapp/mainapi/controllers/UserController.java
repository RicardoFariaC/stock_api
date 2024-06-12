package com.stockapp.mainapi.controllers;
import com.stockapp.mainapi.models.sql.dto.UserResponseWhenQueriedDTO;
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
        try {
            UserModel user = this.userService.findById(id);
            return ResponseEntity.ok(new UserResponseWhenQueriedDTO(user.getUsername(), user.getEmail()));
        } catch(Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
