package com.financehub.bills.controller;

import com.financehub.bills.connector.InstitutionCheckReport;
import com.financehub.bills.dto.billinstitution.BillInstitutionResponseDTO;
import com.financehub.bills.model.BillInstitution;
import com.financehub.bills.service.BillInstitutionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bills/institutions")
@AllArgsConstructor
public class BillInstitutionController {

    private final BillInstitutionService billInstitutionService;

    @GetMapping
    public ResponseEntity<List<BillInstitutionResponseDTO>> listAllBillInstitutions() {
        List<BillInstitution> institutions = billInstitutionService.listAllBillInstitutions();
        List<BillInstitutionResponseDTO> response = new ArrayList<>();

        institutions.forEach(inst -> {
            BillInstitutionResponseDTO dto = new BillInstitutionResponseDTO(inst);
            response.add(dto);
        });

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idOrKey}")
    public ResponseEntity<BillInstitutionResponseDTO> getBillAccountById(@PathVariable("idOrKey") String id) {
        BillInstitution institution = billInstitutionService.getBillInstitutionByIdOrKey(id);
        return ResponseEntity.ok(new BillInstitutionResponseDTO(institution));
    }

    @GetMapping("/health")
    public ResponseEntity<List<InstitutionCheckReport>> healthCheck() {
        return ResponseEntity.ok(billInstitutionService.healthCheckAllActive());
    }

    @GetMapping("/{idOrKey}/health")
    public ResponseEntity<InstitutionCheckReport> healthSingle(@PathVariable String idOrKey) {
        return ResponseEntity.ok(billInstitutionService.institutionCheckReport(idOrKey));
    }
}
