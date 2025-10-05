package com.financehub.bills.controller;

import com.financehub.bills.dto.billaccount.BillAccountRegisterDTO;
import com.financehub.bills.dto.billaccount.BillAccountResponseDTO;
import com.financehub.bills.service.BillAccountService;
import com.financehub.bills.model.BillAccount;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bills/accounts")
@AllArgsConstructor
public class BillAccountController {

    private final BillAccountService billAccountService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }

    @PostMapping
    public ResponseEntity<BillAccountResponseDTO> createBillAccount(@Valid @RequestBody BillAccountRegisterDTO billAccountDTO) {
        BillAccount account = billAccountService.createBillAccount(billAccountDTO);

        return ResponseEntity.ok(new BillAccountResponseDTO(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillAccountResponseDTO> getBillAccountById(@PathVariable("id") String id) {
        BillAccount account = billAccountService.getBillAccountById(id);

        return ResponseEntity.ok(new BillAccountResponseDTO(account));
    }

    @GetMapping("/owner/{userId}")
    public ResponseEntity<List<BillAccountResponseDTO>> listBillAccountsByUserId(@PathVariable("userId") String userId) {
        List<BillAccountResponseDTO> accounts = billAccountService.listBillAccountsByUserId(userId)
                .stream()
                .map(BillAccountResponseDTO::new)
                .toList();

        return ResponseEntity.ok(accounts);
    }
}
