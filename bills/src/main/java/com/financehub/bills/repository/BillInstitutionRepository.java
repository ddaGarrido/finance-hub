package com.financehub.bills.repository;

import com.financehub.bills.model.BillInstitution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillInstitutionRepository extends JpaRepository<BillInstitution, Long> {

    BillInstitution findByInstitutionKey(String institutionKey);
    List<BillInstitution> findAllByActiveTrue();
}
