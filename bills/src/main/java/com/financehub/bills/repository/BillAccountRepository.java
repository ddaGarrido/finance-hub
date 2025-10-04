package com.financehub.bills.repository;

import com.financehub.bills.model.BillAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BillAccountRepository extends JpaRepository<BillAccount, UUID> {

    List<BillAccount> findByUserId(UUID userId);
}
