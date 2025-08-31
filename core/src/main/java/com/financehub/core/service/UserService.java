package com.financehub.core.service;

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

    public User.ResponseUserDTO createUser(User.CreateUserDTO userDTO) {
        // Check if email already exists
        userRepository.findByEmail(userDTO.email()).ifPresent(u -> {
            throw new IllegalArgumentException("Email already in use");
        });

        String hashedPassword = passwordEncoder.encode(userDTO.password());
        List<BankAccount> bankAccounts = List.of(); // Initialize with empty list
        User newUser = new User(
            UUID.randomUUID(),
            userDTO.username(),
            hashedPassword,
            userDTO.email(),
            userDTO.name(),
            userDTO.role(),
            bankAccounts
        );

        userRepository.save(newUser);

        return new User.ResponseUserDTO(newUser);
    }


    public List<User.ResponseUserListDTO> listUsers() {
        List<User.ResponseUserDTO> users = userRepository.findAll().stream().map(User.ResponseUserDTO::new).toList();
        return users.stream()
                .map(user -> new User.ResponseUserListDTO(user.id(), user.username(), user.email(), user.name(), user.role()))
                .toList();
    }

    public User.ResponseUserDTO getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        return new User.ResponseUserDTO(user.get());
    }

    public User.ResponseUserDTO getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        return new User.ResponseUserDTO(user.get());
    }
}
