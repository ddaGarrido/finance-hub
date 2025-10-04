package com.financehub.bills.repository;

import com.financehub.bills.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {

    List<Bill> findByUserId(UUID userId);
}
