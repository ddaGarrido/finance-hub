package com.financehub.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_users")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class User {

    @Id
    private UUID id;

    private String username;
    private String password; //TODO hash this
    private String email;
    private String name;
    private String role;

    private List<BankAccount> bankAccounts;

    public record CreateUserDTO(@NotBlank String username, @Size(min=6) String password, @Email String email, @NotBlank String name, @NotBlank String role) {}
    public record ResponseUserDTO(UUID id, String username, String email, String name, String role) {
        public ResponseUserDTO(User user) {
            this(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getRole());
        }
    }
    public record ResponseUserListDTO(UUID id, String username, String email, String name, String role) {
        public ResponseUserListDTO(User user) {
            this(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getRole());
        }
    }
}
