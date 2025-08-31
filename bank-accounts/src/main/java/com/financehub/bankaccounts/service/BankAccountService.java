package com.financehub.bankaccounts.service;

import com.financehub.bankaccounts.repository.BankAccountRepository;
import com.financehub.core.model.BankAccount;
import com.financehub.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccount createBankAccount(BankAccount.Create bankAccountDTO) {
        // Check if owner exists
        userRepository.findById(bankAccountDTO.ownerId()).orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        BankAccount newAccount = new BankAccount();
        newAccount.setId(UUID.randomUUID());
        newAccount.setOwnerId(UUID.fromString(bankAccountDTO.ownerId()));
        newAccount.setAccountName(bankAccountDTO.accountName());
        newAccount.setAccountNumber("00012345"); // Placeholder, should be generated or provided
        newAccount.setAccountRouting("11000000"); // Placeholder, should be generated or provided
        newAccount.setBankName("FinanceHub Bank"); // Placeholder, could be dynamic
        newAccount.setBalance(BigDecimal.ZERO);
        newAccount.setCurrency("BRL");

        bankAccountRepository.save(newAccount);

        return newAccount;
    }

    public BankAccount getBankAccountById(UUID id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Bank account not found"));
    }

    public List<BankAccount> listBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public List<BankAccount> listBankAccountsByOwnerId(UUID ownerId) {
        // Check if owner exists
        userRepository.findById(ownerId).orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        return bankAccountRepository.findByOwnerId(ownerId);
    }
}
