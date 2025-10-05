package com.financehub.bankaccounts.controller;

import com.financehub.bankaccounts.service.BankAccountService;
import com.financehub.bankaccounts.dto.BankAccountRegisterDTO;
import com.financehub.bankaccounts.dto.BankAccountResponseDTO;
import com.financehub.bankaccounts.model.BankAccount;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
@AllArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<BankAccountResponseDTO> createBankAccount(@Valid @RequestBody BankAccountRegisterDTO bankAccountDTO) {
        BankAccount account = bankAccountService.createBankAccount(bankAccountDTO);

        return ResponseEntity.ok(new BankAccountResponseDTO(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponseDTO> getBankAccountById(@PathVariable("id") String id) {
        BankAccount account = bankAccountService.getBankAccountById(id);

        return ResponseEntity.ok(new BankAccountResponseDTO(account));
    }

    @GetMapping("/owner/{userId}")
    public ResponseEntity<List<BankAccountResponseDTO>> listBankAccountsByUserId(@PathVariable("userId") String userId) {
        List<BankAccountResponseDTO> accounts = bankAccountService.listBankAccountsByUserId(userId)
                .stream()
                .map(BankAccountResponseDTO::new)
                .toList();

        return ResponseEntity.ok(accounts);
    }
}
