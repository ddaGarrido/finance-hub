package com.financehub.core.dto.auth;

import com.financehub.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AuthRequestDTO {
    private String username;
    private String password;
}
