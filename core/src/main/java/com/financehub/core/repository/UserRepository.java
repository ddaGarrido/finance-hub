package com.financehub.core.repository;

import com.financehub.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private static final Map<UUID, User> userDatabase = new ConcurrentHashMap<>();

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        userDatabase.put(user.getId(), user);
        return user;
    }

    public Collection<User> findAll() {
        return userDatabase.values();
    }
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(userDatabase.get(id));
    }
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userDatabase.get(UUID.fromString(id)));
    }
    public Optional<User> findByEmail(String email) {
        return userDatabase.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
    public Optional<User> findByUsername(String username) {
        return userDatabase.values().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }
}
