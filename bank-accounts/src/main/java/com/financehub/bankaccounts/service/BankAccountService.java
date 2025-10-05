package com.financehub.bankaccounts.service;

import com.financehub.bankaccounts.repository.BankAccountRepository;
import com.financehub.bankaccounts.dto.BankAccountRegisterDTO;
import com.financehub.core.error.NotFoundException;
import com.financehub.bankaccounts.model.BankAccount;
import com.financehub.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccount createBankAccount(BankAccountRegisterDTO bankAccountDTO) {
//        Optional<User> owner = null;//userRepository.findById(UUID.fromString(bankAccountDTO.getUserId()));
//
//        if (owner.isEmpty()) {
//            throw new NotFoundException("Owner not found");
//        }
//
//        BankAccount newAccount = bankAccountDTO.toEntity();
//        List<BankAccount> existingAccounts = null;//bankAccountRepository.findByUserId(owner.get().getId());
//        existingAccounts.add(newAccount);
//
//        newAccount = bankAccountRepository.save(newAccount);
//
//        return newAccount;
        return null;
    }

    public BankAccount getBankAccountById(String id) {
        return bankAccountRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException("Bank account not found"));
    }

    public List<BankAccount> listBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public List<BankAccount> listBankAccountsByUserId(String userId) {
        List<BankAccount> accounts = bankAccountRepository.findByUserId(Long.valueOf(userId));

        if (accounts.isEmpty()) {
            throw new NotFoundException("No bank accounts found for user with ID: " + userId);
        }
        return accounts;
    }
}
