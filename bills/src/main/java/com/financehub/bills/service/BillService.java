package com.financehub.bills.service;

import com.financehub.bills.model.Bill;
import com.financehub.bills.model.BillInstitution;
import com.financehub.bills.repository.BillInstitutionRepository;
import com.financehub.bills.repository.BillRepository;
import com.financehub.core.error.NotFoundException;
import com.financehub.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BillService {

    private final BillRepository billRepository;
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

    public Bill getBillAccountById(UUID id) {
        return billRepository.findById(id).orElseThrow(() -> new NotFoundException("Bill not found"));
    }

    public List<Bill> listBillAccountsByUserId(UUID userId) {
        // Check if owner exists
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Owner not found"));
        // Assuming BillAccount has a userId field and a corresponding method in the repository
        return billRepository.findByUserId(userId);
    }
}
