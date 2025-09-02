package com.financehub.core.dto.user;

import com.financehub.core.model.BankAccount;
import com.financehub.core.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UserResponseDTO {
    private String id;
    private String username;
    private String email;
    private String name;
    private String role;
    private List<BankAccount> bankAccounts;

    public UserResponseDTO(User user) {
        this.id = user.getId().toString();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.role = user.getRole();
        this.bankAccounts = user.getBankAccounts();
    }
}
