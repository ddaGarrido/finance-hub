package com.financehub.core.controller;

import com.financehub.core.dto.auth.AuthRequestDTO;
import com.financehub.core.dto.auth.AuthResponseDTO;
import com.financehub.core.dto.user.UserRegisterDTO;
import com.financehub.core.dto.user.UserResponseDTO;
import com.financehub.core.model.User;
import com.financehub.core.service.JwtsService;
import com.financehub.core.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtsService jwtsService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody UserRegisterDTO registerRequestDTO) {
        User user = userService.createUser(registerRequestDTO);
        AuthRequestDTO loginRequestDTO = new AuthRequestDTO(user.getUsername(), registerRequestDTO.getPassword());

        AuthResponseDTO responseDTO = login(loginRequestDTO).getBody();

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        User user = userService.getUserByUsername(loginRequestDTO.getUsername());
        final String jwtToken = jwtsService.generateToken(user);
        final String userId = jwtsService.extractUserId(jwtToken);

        return ResponseEntity.ok(new AuthResponseDTO(userId, jwtToken));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        String username = jwtsService.extractUsername(token);
        User user = userService.getUserByUsername(username);
        UserResponseDTO userResponseDTO = new UserResponseDTO(user);

        return ResponseEntity.ok(userResponseDTO);
    }
}
