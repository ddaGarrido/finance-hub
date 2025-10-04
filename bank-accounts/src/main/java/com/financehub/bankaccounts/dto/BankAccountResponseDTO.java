package com.financehub.bankaccounts.dto;

import com.financehub.bankaccounts.model.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BankAccountResponseDTO {
    private String id;
    private String userId;
    private String accountName;
    private String accountNumber;
    private String accountRouting;
    private String accountType;
    private String institutionName;
    private String holderName;
    private String balance;
    private String currency;
    private Boolean active;

    public BankAccountResponseDTO(BankAccount account) {
        this.id = account.getId().toString();
        this.userId = account.getUserId().toString();
        this.accountName = account.getAccountName();
        this.accountNumber = account.getAccountNumber();
        this.accountRouting = account.getAccountRouting();
        this.accountType = account.getAccountType();
        this.institutionName = account.getInstitutionName();
        this.holderName = account.getHolderName();
        this.balance = account.getBalance().toString();
        this.currency = account.getCurrency();
        this.active = account.getActive();
    }
}
