package com.financehub.core.controller;

import com.financehub.core.model.User;
import com.financehub.core.service.JwtsService;
import com.financehub.core.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtsService jwtsService;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, UserService userService, JwtsService jwtsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.jwtsService = jwtsService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody User.CreateUserDTO registerRequestDTO) {
        User.ResponseUserDTO user = userService.createUser(registerRequestDTO);

        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(registerRequestDTO.username(), registerRequestDTO.password());
        LoginResponseDTO responseDTO = login(loginRequestDTO).getBody();

        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO(user.id(), responseDTO.token());

        return ResponseEntity.ok(registerResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.username());
        final String jwtToken = jwtsService.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponseDTO(jwtToken));
    }

    @GetMapping("/me")
    public ResponseEntity<User.ResponseUserDTO> getCurrentUser(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        String username = jwtsService.extractUsername(token);
        User.ResponseUserDTO user = userService.getUserByUsername(username);

        return ResponseEntity.ok(user);
    }

    public record LoginRequestDTO(String username, String password) {}
    public record LoginResponseDTO(String token) {}
    public record RegisterResponseDTO(UUID id, String token) {}
}
