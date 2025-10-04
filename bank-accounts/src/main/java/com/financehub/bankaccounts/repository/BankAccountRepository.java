package com.financehub.bankaccounts.repository;

import com.financehub.bankaccounts.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);
    List<BankAccount> findByUserId(UUID userId);
}
