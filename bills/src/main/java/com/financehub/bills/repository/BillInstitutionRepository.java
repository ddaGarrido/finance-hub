package com.financehub.bills.repository;

import com.financehub.bills.model.BillInstitution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillInstitutionRepository extends JpaRepository<BillInstitution, UUID> {

    BillInstitution findByProviderKey(String providerKey);
}
