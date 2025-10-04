package com.financehub.bills.service;

import com.financehub.bills.dto.BillAccountRegisterDTO;
import com.financehub.bills.repository.BillAccountRepository;
import com.financehub.core.error.NotFoundException;
import com.financehub.bills.model.BillAccount;
import com.financehub.core.model.User;
import com.financehub.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BillAccountService {

    private final BillAccountRepository billAccountRepository;
    private final UserRepository userRepository;

    public BillAccount createBillAccount(BillAccountRegisterDTO billAccountDTO) {
        Optional<User> owner = userRepository.findById(UUID.fromString(billAccountDTO.getUserId()));

        if (owner.isEmpty()) {
            throw new NotFoundException("Owner not found");
        }

        BillAccount newAccount = billAccountDTO.toEntity();

        newAccount = billAccountRepository.save(newAccount);

        return newAccount;
    }

    public BillAccount getBillAccountById(UUID id) {
        return billAccountRepository.findById(id).orElseThrow(() -> new NotFoundException("Bill account not found"));
    }

    public List<BillAccount> listBillAccountsByUserId(UUID userId) {
        // Check if owner exists
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Owner not found"));
        // Assuming BillAccount has a userId field and a corresponding method in the repository
        return billAccountRepository.findByUserId(userId);
    }
}
