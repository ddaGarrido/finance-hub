package com.financehub.bankaccounts.dto;

import com.financehub.bankaccounts.model.BankAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BankAccountRegisterDTO {

    @NotBlank(message = "User ID is required")
    private String userId;
    @NotBlank(message = "Bank Id is required")
    private String bankInstitutionId;
    @NotBlank(message = "Account Name is required")
    private String accountName;
    @NotBlank(message = "Account Number is required")
    private String accountNumber;
    @NotBlank(message = "Account Routing is required")
    private String accountRouting;
    @NotBlank(message = "Account Type is required")
    private String accountType;
    @NotBlank(message = "Holder Name is required")
    private String holderName;
    @NotBlank(message = "Currency is required")
    private String currency;

    public BankAccount toEntity() {
        BankAccount account = new BankAccount();
        account.setUserId(Long.valueOf(this.userId));
        account.setName(this.accountName);
        account.setNumber(this.accountNumber);
        account.setRouting(this.accountRouting);
        account.setType(this.accountType);
        account.setHolderName(this.holderName);
        account.setBalance(BigDecimal.ZERO); // Initial balance set to zero
        account.setCurrency(this.currency);
        account.setActive(true); // New accounts are active by default

        return account;
    }
}
