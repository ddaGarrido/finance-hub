package com.financehub.bills.repository;

import com.financehub.bills.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByUserId(Long userId);
}
