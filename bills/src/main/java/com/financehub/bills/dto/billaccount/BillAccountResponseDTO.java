package com.financehub.bills.dto.billaccount;

import com.financehub.bills.model.BillAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BillAccountResponseDTO {

    private String id;
    private String billInstitutionId;
    private String userId;
    private String name;
    private String username;
    private String password;
    private Boolean active;

    public BillAccountResponseDTO(BillAccount billAccount) {
        this.id = billAccount.getId().toString();
        this.billInstitutionId = billAccount.getBillInstitutionId().toString();
        this.userId = billAccount.getUserId().toString();
        this.name = billAccount.getName();
        this.username = billAccount.getUsername();
        this.password = billAccount.getPassword();
        this.active = billAccount.getActive();
    }
}
