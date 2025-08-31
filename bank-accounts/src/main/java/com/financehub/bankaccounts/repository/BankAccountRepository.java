package com.financehub.bankaccounts.repository;

import com.financehub.core.model.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class BankAccountRepository {
    private static final Map<UUID, BankAccount> db = new ConcurrentHashMap<>();

    public BankAccount save(BankAccount bankAccount) {
        if (bankAccount.getId() == null) {
            bankAccount.setId(UUID.randomUUID());
        }
        db.put(bankAccount.getId(), bankAccount);
        return bankAccount;
    }

    public List<BankAccount> findAll() {
        return db.values().stream().toList();
    }

    public Optional<BankAccount> findById(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    public List<BankAccount> findByOwnerId(UUID ownerId) {
        return db.values().stream()
                .filter(acc -> acc.getOwnerId().equals(ownerId))
                .collect(Collectors.toList());
    }

    public Optional<BankAccount> findByIdAndOwnerId(UUID accountId, UUID ownerId) {
        return Optional.ofNullable(db.get(accountId))
                .filter(acc -> acc.getOwnerId().equals(ownerId));
    }
}
