package com.financehub.bankaccounts.service;

import com.financehub.bankaccounts.repository.BankAccountRepository;
import com.financehub.core.dto.bankaccount.BankAccountRegisterDTO;
import com.financehub.core.model.BankAccount;
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
        Optional<User> owner = userRepository.findById(bankAccountDTO.getOwnerId());

        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Owner not found");
        }

        BankAccount newAccount = bankAccountDTO.toEntity();
        List<BankAccount> existingAccounts = bankAccountRepository.findByOwnerId(owner.get().getId());
        existingAccounts.add(newAccount);
        owner.get().setBankAccounts(existingAccounts);

        bankAccountRepository.save(newAccount);
        userRepository.save(owner.get());

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
