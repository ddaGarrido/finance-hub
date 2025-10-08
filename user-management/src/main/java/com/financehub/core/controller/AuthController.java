package com.financehub.core.controller;

import com.financehub.core.dto.auth.AuthRequestDTO;
import com.financehub.core.dto.auth.AuthResponseDTO;
import com.financehub.core.dto.user.UserRegisterDTO;
import com.financehub.core.dto.user.UserResponseDTO;
import com.financehub.core.error.UnauthorizedException;
import com.financehub.core.model.User;
import com.financehub.core.service.JwtsService;
import com.financehub.core.service.UserService;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtsService jwtsService;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final MeterRegistry meterRegistry;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody UserRegisterDTO registerRequestDTO) {
        User user = userService.createUser(registerRequestDTO);
        AuthRequestDTO loginRequestDTO = new AuthRequestDTO(user.getUsername(), registerRequestDTO.getPassword());

        AuthResponseDTO responseDTO = login(loginRequestDTO).getBody();

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO loginRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
            );
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid username or password");
        }

        User user = userService.getUserByUsername(loginRequestDTO.getUsername());
        final String jwtToken = jwtsService.generateToken(user);
        final String userId = jwtsService.extractUserId(jwtToken);

        return ResponseEntity.ok(new AuthResponseDTO(userId, jwtToken));
    }

    private static final Random RANDOM = new Random();
    private static final List<HttpStatus> RANDOM_STATUSES = List.of(
            // 2xx Success
            HttpStatus.OK, HttpStatus.CREATED, HttpStatus.ACCEPTED,
            // 3xx Redirection
            HttpStatus.MOVED_PERMANENTLY, HttpStatus.FOUND, HttpStatus.NOT_MODIFIED,
            // 4xx Client Error
            HttpStatus.BAD_REQUEST, HttpStatus.UNAUTHORIZED, HttpStatus.NOT_FOUND, HttpStatus.CONFLICT,
            // 5xx Server Error
            HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.BAD_GATEWAY, HttpStatus.SERVICE_UNAVAILABLE
    );

    private ResponseEntity<Map<String, String>> generateRandomResponse(String endpointName) {
        // Select a random HttpStatus from our predefined list.
        HttpStatus randomStatus = RANDOM_STATUSES.get(RANDOM.nextInt(RANDOM_STATUSES.size()));

        log.info("{} check requested", endpointName);
        log.info("{} check result: {}", endpointName, randomStatus);

//        // Increment general API and specific endpoint counters.
//        meterRegistry.counter("C_Api").increment();
//        meterRegistry.counter("C_Api_" + endpointName).increment();

        // Determine the status code family (2xx, 3xx, etc.) and increment the correct counter.
        String statusCodeFamily;
        if (randomStatus.is2xxSuccessful()) {
            statusCodeFamily = "2xx";
        } else if (randomStatus.is3xxRedirection()) {
            statusCodeFamily = "3xx";
        } else if (randomStatus.is4xxClientError()) {
            statusCodeFamily = "4xx";
        } else if (randomStatus.is5xxServerError()) {
            statusCodeFamily = "5xx";
        } else {
            statusCodeFamily = "other";
        }

//        meterRegistry.counter("C_Api_" + endpointName + "_" + statusCodeFamily).increment();

        return new ResponseEntity<>(Map.of("status", randomStatus.getReasonPhrase()), randomStatus);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        // In a stateless JWT authentication system, logout can be handled on the client side
        // by simply deleting the token. If token invalidation is required, implement a token
        // blacklist mechanism here.
        return generateRandomResponse("Logout");
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
