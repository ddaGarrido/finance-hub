package com.financehub.core.controller;

import com.financehub.core.dto.user.UserRegisterDTO;
import com.financehub.core.dto.user.UserResponseDTO;
import com.financehub.core.model.User;
import com.financehub.core.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRegisterDTO createUserDTO) {
        User newUser = service.createUser(createUserDTO);
        UserResponseDTO response = new UserResponseDTO(
                newUser.getId().toString(),
                newUser.getUsername(),
                newUser.getEmail(),
                newUser.getName(),
                newUser.getRole(),
                newUser.getBankAccounts()
        );

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        List<User> users = service.listUsers();
        return ResponseEntity.ok(users.stream().map(UserResponseDTO::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        User user = service.getUserById(id);
        UserResponseDTO response = new UserResponseDTO(user);

        return ResponseEntity.ok(response);
    }
}
