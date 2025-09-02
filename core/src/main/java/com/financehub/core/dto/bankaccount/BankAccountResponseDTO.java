package com.financehub.core.dto.bankaccount;

import com.financehub.core.model.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BankAccountResponseDTO {
    private String id;
    private String ownerId;
    private String accountName;
    private String accountNumber;
    private String accountRouting;
    private String bankName;
    private String balance;
    private String currency;

    public BankAccountResponseDTO(BankAccount account) {
        this.id = account.getId().toString();
        this.ownerId = account.getOwnerId().toString();
        this.accountName = account.getAccountName();
        this.accountNumber = account.getAccountNumber();
        this.accountRouting = account.getAccountRouting();
        this.bankName = account.getBankName();
        this.balance = account.getBalance().toString();
        this.currency = account.getCurrency();
    }
}
