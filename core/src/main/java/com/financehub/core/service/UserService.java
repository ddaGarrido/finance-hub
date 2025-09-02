package com.financehub.core.service;

import com.financehub.core.dto.user.UserRegisterDTO;
import com.financehub.core.model.BankAccount;
import com.financehub.core.model.User;
import com.financehub.core.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserRegisterDTO userDTO) {
        // Check if email already exists
        userRepository.findByEmail(userDTO.getEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("Email already in use");
        });

        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        List<BankAccount> bankAccounts = List.of(); // Initialize with empty list
        User newUser = new User(
            UUID.randomUUID(),
            userDTO.getUsername(),
            hashedPassword,
            userDTO.getEmail(),
            userDTO.getName(),
            userDTO.getRole(),
            bankAccounts
        );

        userRepository.save(newUser);

        return newUser;
    }


    public List<User> listUsers() {
        return userRepository.findAll().stream().toList();
    }

    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        return user.get();
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        return user.get();
    }
}
