package com.financehub.bills.dto.bill;

import com.financehub.bills.model.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BillResponseDTO {

    private String id;
    private String billInstitutionId;
    private String userId;
    private String billAccountId;
    private String type;
    private String status;
    private String description;
    private String dueDate;
    private String paidAt;
    private String amount;
    private String currency;
    private Object tagsJson;
    private Object metadataJson;

    public BillResponseDTO(Bill bill) {
        this.id = bill.getId().toString();
        this.billInstitutionId = bill.getBillInstitutionId().toString();
        this.userId = bill.getUserId().toString();
        this.billAccountId = bill.getBillAccountId().toString();
        this.type = bill.getType();
        this.status = bill.getStatus();
        this.description = bill.getDescription();
        this.dueDate = bill.getDueDate().toString();
        this.paidAt = String.valueOf(bill.getPaidAt());
        this.amount = String.valueOf(bill.getAmount());
        this.currency = bill.getCurrency();
        this.tagsJson = bill.getTagsJson();
        this.metadataJson = bill.getMetadataJson();
    }
}
