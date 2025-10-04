package com.financehub.bills.dto;

import com.financehub.bills.model.BillAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BillAccountResponseDTO {

    private String id;
    private String userId;
    private String billAccountName;
    private String billInstitutionName;
    private String billUsername;
    private String billPassword;
    private Boolean active;

    public BillAccountResponseDTO(BillAccount billAccount) {
        this.id = billAccount.getId().toString();
        this.userId = billAccount.getUserId().toString();
        this.billAccountName = billAccount.getBillAccountName();
        this.billInstitutionName = billAccount.getBillInstitutionName();
        this.billUsername = billAccount.getBillUsername();
        this.billPassword = billAccount.getBillPassword();
        this.active = billAccount.getActive();
    }
}
