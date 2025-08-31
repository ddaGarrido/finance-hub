package com.financehub.core.controller;

import com.financehub.core.model.User;
import com.financehub.core.model.User.ResponseUserDTO;
import com.financehub.core.model.User.CreateUserDTO;
import com.financehub.core.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseUserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        ResponseUserDTO response = service.createUser(createUserDTO);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<User.ResponseUserListDTO>> listUsers() {
        List<User.ResponseUserListDTO> users = service.listUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable UUID id) {
        ResponseUserDTO response = service.getUserById(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }
}
