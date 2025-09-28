package com.financehub.core.dto.bankaccount;

import com.financehub.core.model.BankAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BankAccountRegisterDTO {

    @NotBlank(message = "User ID is required")
    private String userId;
    @NotBlank(message = "Account Name is required")
    private String accountName;
    @NotBlank(message = "Account Number is required")
    private String accountNumber;
    @NotBlank(message = "Account Routing is required")
    private String accountRouting;
    @NotBlank(message = "Account Type is required")
    private String accountType;
    @NotBlank(message = "Institution Name is required")
    private String institutionName;
    @NotBlank(message = "Holder Name is required")
    private String holderName;
    @NotBlank(message = "Currency is required")
    private String currency;

    public BankAccount toEntity() {
        BankAccount account = new BankAccount();
        account.setUserId(UUID.fromString(this.userId));
        account.setAccountName(this.accountName);
        account.setAccountNumber(this.accountNumber);
        account.setAccountRouting(this.accountRouting);
        account.setAccountType(this.accountType);
        account.setInstitutionName(this.institutionName);
        account.setHolderName(this.holderName);
        account.setBalance(BigDecimal.ZERO); // Initial balance set to zero
        account.setCurrency(this.currency);
        account.setActive(true); // New accounts are active by default

        return account;
    }
}
