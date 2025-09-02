package com.financehub.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
}
