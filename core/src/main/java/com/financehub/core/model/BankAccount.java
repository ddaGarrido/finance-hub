package com.financehub.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "bank_accounts", schema = "bank_accounts_mgmt")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BankAccount extends DBEntity{

    @Column(nullable = false)
    private UUID ownerId;

    @Column(nullable = false, length = 120)
    private String accountName;

    @Column(nullable = false, length = 30, unique = true)
    private String accountNumber;

    @Column(nullable = false, length = 9)
    private String accountRouting;

    @Column(nullable = false, length = 120)
    private String bankName;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false, length = 3)
    private String currency;
}
