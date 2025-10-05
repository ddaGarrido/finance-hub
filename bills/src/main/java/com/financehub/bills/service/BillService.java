package com.financehub.bills.service;

import com.financehub.bills.model.Bill;
import com.financehub.bills.repository.BillRepository;
import com.financehub.core.error.NotFoundException;
import com.financehub.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Bill getBillAccountById(String id) {
        Optional<Bill> bill = billRepository.findById(Long.valueOf(id));

        if (bill.isEmpty()) {
            throw new NotFoundException("Bill not found");
        }
        return bill.get();
    }

    public List<Bill> listBillAccountsByUserId(String userId) {
        List<Bill> bills = billRepository.findByUserId(Long.valueOf(userId));

        if (bills.isEmpty()) {
            throw new NotFoundException("No bills found for the given user ID");
        }
        return bills;
    }
}
