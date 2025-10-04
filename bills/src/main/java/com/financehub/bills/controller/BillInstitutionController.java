package com.financehub.bills.controller;

import com.financehub.bills.dto.billaccount.BillAccountRegisterDTO;
import com.financehub.bills.dto.billaccount.BillAccountResponseDTO;
import com.financehub.bills.dto.billinstitution.BillInstitutionResponseDTO;
import com.financehub.bills.model.BillAccount;
import com.financehub.bills.model.BillInstitution;
import com.financehub.bills.service.BillInstitutionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/bills/institutions")
@AllArgsConstructor
public class BillInstitutionController {

    private final BillInstitutionService billInstitutionService;

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

    @GetMapping("/{idOrKey}")
    public ResponseEntity<BillInstitutionResponseDTO> getBillAccountById(@PathVariable("idOrKey") String id) {
        BillInstitution institution = billInstitutionService.getBillInstitutionByIdOrKey(id);

        return ResponseEntity.ok(new BillInstitutionResponseDTO(institution));
    }
}
