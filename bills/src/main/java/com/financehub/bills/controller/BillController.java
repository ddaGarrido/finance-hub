package com.financehub.bills.controller;

import com.financehub.bills.dto.bill.BillResponseDTO;
import com.financehub.bills.model.Bill;
import com.financehub.bills.service.BillService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bills/bill")
@AllArgsConstructor
public class BillController {

    private final BillService billService;

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }

//    @PostMapping
//    public ResponseEntity<BillAccountResponseDTO> createBillAccount(@Valid @RequestBody BillAccountRegisterDTO billAccountDTO) {
//        BillAccount account = billAccountService.createBillAccount(billAccountDTO);
//
//        return ResponseEntity.ok(new BillAccountResponseDTO(account));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponseDTO> getBillAccountById(@PathVariable("id") String id) {
        Bill bill = billService.getBillAccountById(id);

        return ResponseEntity.ok(new BillResponseDTO(bill));
    }

    @GetMapping("/owner/{userId}")
    public ResponseEntity<List<BillResponseDTO>> listBillAccountsByUserId(@PathVariable("userId") String userId) {
        List<BillResponseDTO> bills = billService.listBillAccountsByUserId(userId)
                .stream()
                .map(BillResponseDTO::new)
                .toList();

        return ResponseEntity.ok(bills);
    }
}
