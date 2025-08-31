package com.financehub.bankaccounts.controller;

import com.financehub.bankaccounts.service.BankAccountService;
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
    public ResponseEntity<BankAccount.Response> createBankAccount(@RequestBody BankAccount.Create bankAccountDTO) {
        BankAccount account = bankAccountService.createBankAccount(bankAccountDTO);

        return ResponseEntity.ok(new BankAccount.Response(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount.Response> getBankAccountById(@PathVariable("id") String id) {
        BankAccount account = bankAccountService.getBankAccountById(UUID.fromString(id));

        return ResponseEntity.ok(new BankAccount.Response(account));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<BankAccount.Response>> listBankAccountsByOwnerId(@PathVariable("ownerId") String ownerId) {
        List<BankAccount.Response> accounts = bankAccountService.listBankAccountsByOwnerId(UUID.fromString(ownerId))
                .stream()
                .map(BankAccount.Response::new)
                .toList();

        return ResponseEntity.ok(accounts);
    }
}
