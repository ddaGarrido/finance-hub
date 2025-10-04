package com.financehub.bills.service;

import com.financehub.bills.model.BillInstitution;
import com.financehub.bills.repository.BillInstitutionRepository;
import com.financehub.core.error.NotFoundException;
import com.financehub.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BillInstitutionService {

    private final BillInstitutionRepository billInstitutionRepository;
    private final UserRepository userRepository;

//    public BillAccount createBillAccount(BillAccountRegisterDTO billAccountDTO) {
//        Optional<User> owner = userRepository.findById(UUID.fromString(billAccountDTO.getUserId()));
//
//        if (owner.isEmpty()) {
//            throw new NotFoundException("Owner not found");
//        }
//
//        BillAccount newAccount = billAccountDTO.toEntity();
//
//        newAccount = billAccountRepository.save(newAccount);
//
//        return newAccount;
//    }

    public BillInstitution getBillInstitutionByIdOrKey(String id) {
        BillInstitution institution = null;
        try {
            institution = billInstitutionRepository.findById(UUID.fromString(id))
                    .orElse(null);
        } catch (Exception e) {
            institution = billInstitutionRepository.findByProviderKey(id.toLowerCase());
            if (institution == null) {
                throw new NotFoundException("Bill institution not found");
            }
        }
        return institution;
    }
}
