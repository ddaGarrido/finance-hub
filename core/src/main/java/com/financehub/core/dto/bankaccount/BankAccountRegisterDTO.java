package com.financehub.core.dto.bankaccount;

import com.financehub.core.model.BankAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BankAccountRegisterDTO {

    @NotBlank(message = "Owner ID is required")
    private String ownerId;
    @NotBlank(message = "Account Name is required")
    private String accountName;
    @NotBlank(message = "Account Number is required")
    private String accountNumber;
    @NotBlank(message = "Account Routing is required")
    private String accountRouting;
    @NotBlank(message = "Bank Name is required")
    private String bankName;
    @NotBlank(message = "Currency is required")
    private String currency;

    public BankAccount toEntity() {
        return new BankAccount(null, UUID.fromString(this.ownerId), this.accountName, this.accountNumber, this.accountRouting, this.bankName, java.math.BigDecimal.ZERO, this.currency);
    }
}
