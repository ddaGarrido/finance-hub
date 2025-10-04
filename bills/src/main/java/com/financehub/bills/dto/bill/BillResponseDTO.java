package com.financehub.bills.dto.bill;

import com.financehub.bills.model.Bill;
import com.financehub.bills.model.BillInstitution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BillResponseDTO {

    private String id;
    private String userId;
    private String accountId;
    private String institutionId;
    private String type;
    private String status;
    private String description;
    private String dueDate;
    private String paidAt;
    private String barcode;
    private String beneficiaryName;
    private String beneficiaryTaxId;
    private String payerTaxId;
    private String amount;
    private String currency;
    private Object tagsJson;
    private Object metadataJson;

    public BillResponseDTO(Bill bill) {
        this.id = bill.getId().toString();
        this.userId = bill.getUserId().toString();
        this.accountId = String.valueOf(bill.getAccountId());
        this.institutionId = bill.getInstitutionId().toString();
        this.type = bill.getType();
        this.status = bill.getStatus();
        this.description = bill.getDescription();
        this.dueDate = bill.getDueDate().toString();
        this.paidAt = String.valueOf(bill.getPaidAt());
        this.barcode = bill.getBarcode();
        this.beneficiaryName = bill.getBeneficiaryName();
        this.beneficiaryTaxId = bill.getBeneficiaryTaxId();
        this.payerTaxId = bill.getPayerTaxId();
        this.amount = String.valueOf(bill.getAmount());
        this.currency = bill.getCurrency();
        this.tagsJson = bill.getTagsJson();
        this.metadataJson = bill.getMetadataJson();
    }
}
