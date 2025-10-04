package com.financehub.bankaccounts.service;

import com.financehub.bankaccounts.repository.BankAccountRepository;
import com.financehub.bankaccounts.dto.BankAccountRegisterDTO;
import com.financehub.core.error.NotFoundException;
import com.financehub.bankaccounts.model.BankAccount;
import com.financehub.core.model.User;
import com.financehub.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccount createBankAccount(BankAccountRegisterDTO bankAccountDTO) {
        Optional<User> owner = userRepository.findById(UUID.fromString(bankAccountDTO.getUserId()));

        if (owner.isEmpty()) {
            throw new NotFoundException("Owner not found");
        }

        BankAccount newAccount = bankAccountDTO.toEntity();
        List<BankAccount> existingAccounts = bankAccountRepository.findByUserId(owner.get().getId());
        existingAccounts.add(newAccount);

        newAccount = bankAccountRepository.save(newAccount);

        return newAccount;
    }

    public BankAccount getBankAccountById(UUID id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new NotFoundException("Bank account not found"));
    }

    public List<BankAccount> listBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public List<BankAccount> listBankAccountsByUserId(UUID userId) {
        // Check if owner exists
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Owner not found"));
        return bankAccountRepository.findByUserId(userId);
    }
}
