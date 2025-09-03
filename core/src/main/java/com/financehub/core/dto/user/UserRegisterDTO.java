package com.financehub.core.dto.user;

import com.financehub.core.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserRegisterDTO {
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50)
    public String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 120)
    public String password;

    @NotBlank(message = "Email is required")
    public String email;

    @NotBlank(message = "Name is required")
    public String name;

    @NotBlank(message = "Role is required")
    public String role;

    public User toEntity() {
        return new User(null, this.username, this.password, this.email, this.name, this.role, null);
    }
}
