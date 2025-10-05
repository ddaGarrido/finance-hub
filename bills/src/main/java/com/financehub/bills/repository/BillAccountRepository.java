package com.financehub.bills.repository;

import com.financehub.bills.model.BillAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillAccountRepository extends JpaRepository<BillAccount, Long> {

    List<BillAccount> findByUserId(Long userId);
}
