package com.financehub.bills.dto;

import com.financehub.core.dto.bankaccount.BankAccountResponseDTO;
import com.financehub.core.model.BillAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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
