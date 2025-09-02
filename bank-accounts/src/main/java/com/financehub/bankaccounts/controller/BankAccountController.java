package com.financehub.bankaccounts.controller;

import com.financehub.bankaccounts.service.BankAccountService;
import com.financehub.core.dto.bankaccount.BankAccountRegisterDTO;
import com.financehub.core.dto.bankaccount.BankAccountResponseDTO;
import com.financehub.core.model.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bank-accounts")
@AllArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public ResponseEntity<BankAccountResponseDTO> createBankAccount(@RequestBody BankAccountRegisterDTO bankAccountDTO) {
        BankAccount account = bankAccountService.createBankAccount(bankAccountDTO);

        return ResponseEntity.ok(new BankAccountResponseDTO(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponseDTO> getBankAccountById(@PathVariable("id") String id) {
        BankAccount account = bankAccountService.getBankAccountById(UUID.fromString(id));

        return ResponseEntity.ok(new BankAccountResponseDTO(account));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<BankAccountResponseDTO>> listBankAccountsByOwnerId(@PathVariable("ownerId") String ownerId) {
        List<BankAccountResponseDTO> accounts = bankAccountService.listBankAccountsByOwnerId(UUID.fromString(ownerId))
                .stream()
                .map(BankAccountResponseDTO::new)
                .toList();

        return ResponseEntity.ok(accounts);
    }
}
