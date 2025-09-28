package com.financehub.core.service;

import com.financehub.core.dto.user.UserRegisterDTO;
import com.financehub.core.error.ConflictException;
import com.financehub.core.error.NotFoundException;
import com.financehub.core.model.BankAccount;
import com.financehub.core.model.User;
import com.financehub.core.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(UserRegisterDTO userDTO) {
        // Check if email already exists
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ConflictException("Username already in use");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new ConflictException("Email already in use");
        }

        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        List<BankAccount> bankAccounts = List.of(); // Initialize with empty list
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(hashedPassword);
        newUser.setEmail(userDTO.getEmail());
        newUser.setName(userDTO.getName());
        newUser.setRole(userDTO.getRole());

        newUser = userRepository.save(newUser);

        return newUser;
    }


    public List<User> listUsers() {
        return userRepository.findAll().stream().toList();
    }

    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(UUID.fromString(id));

        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        return user.get();
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        return user.get();
    }
}
