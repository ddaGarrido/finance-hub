package com.financehub.bankaccounts.model;

import com.financehub.core.model.DBEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "bank_accounts", schema = "bank_accounts")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BankAccount extends DBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_accounts_id_seq")
    @SequenceGenerator(name = "bank_accounts_id_seq", sequenceName = "bank_accounts.bank_accounts_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Long bankInstitutionId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 30, unique = true)
    private String number;

    @Column(nullable = false, length = 9)
    private String routing;

    @Column(nullable = false, length = 10)
    private String type;

    @Column(nullable = false, length = 120)
    private String holderName;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false)
    private Boolean active;
}
