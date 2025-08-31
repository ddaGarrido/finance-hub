package com.financehub.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "app_bank_accounts")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BankAccount {

    @Id
    private UUID id;
    private UUID ownerId;
    private String accountName;
    private String accountNumber;
    private String accountRouting;
    private String bankName;
    private BigDecimal balance;
    private String currency;

    public record Create(@NotBlank String ownerId, @NotBlank String accountName) {}
    public record Response(UUID id, UUID ownerId, String accountName, String accountNumber, String accountRouting, String bankName, BigDecimal balance, String currency) {
        public Response(BankAccount account) {
            this(account.getId(), account.getOwnerId(), account.getAccountName(), account.getAccountNumber(), account.getAccountRouting(), account.getBankName(), account.getBalance(), account.getCurrency());
        }
    }
}
