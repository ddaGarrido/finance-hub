package com.financehub.bills.dto;

import com.financehub.bills.model.BillAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BillAccountRegisterDTO {

    @NotBlank(message = "User ID is required")
    private String userId;
    @NotBlank(message = "Bill Account Name is required")
    private String billAccountName;
    @NotBlank(message = "Bill Institution Name is required")
    private String billInstitutionName;
    @NotBlank(message = "Bill Username is required")
    private String billUsername;
    @NotBlank(message = "Bill Password is required")
    private String billPassword;

    public BillAccount toEntity() {
        BillAccount billAccount = new BillAccount();
        billAccount.setUserId(UUID.fromString(this.userId));
        billAccount.setBillAccountName(this.billAccountName);
        billAccount.setBillInstitutionName(this.billInstitutionName);
        billAccount.setBillUsername(this.billUsername);
        billAccount.setBillPassword(this.billPassword);
        billAccount.setActive(true); // New bill accounts are active by default

        return billAccount;
    }
}
