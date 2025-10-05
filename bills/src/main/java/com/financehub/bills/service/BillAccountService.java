package com.financehub.bills.service;

import com.financehub.bills.dto.billaccount.BillAccountRegisterDTO;
import com.financehub.bills.repository.BillAccountRepository;
import com.financehub.core.error.NotFoundException;
import com.financehub.bills.model.BillAccount;
import com.financehub.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillAccountService {

    private final BillAccountRepository billAccountRepository;
    private final UserRepository userRepository;

    public BillAccount createBillAccount(BillAccountRegisterDTO billAccountDTO) {
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
        return null;
    }

    public BillAccount getBillAccountById(String id) {
        Optional<BillAccount> account = billAccountRepository.findById(Long.valueOf(id));

        if (account.isEmpty()) {
            throw new NotFoundException("Bill account not found");
        }
        return account.get();
    }

    public List<BillAccount> listBillAccountsByUserId(String userId) {
        List<BillAccount> accounts = billAccountRepository.findByUserId(Long.valueOf(userId));

        if (accounts.isEmpty()) {
            throw new NotFoundException("No bill accounts found for the given user ID");
        }
        return accounts;
    }
}
