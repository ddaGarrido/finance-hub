package com.financehub.bills.dto.billaccount;

import com.financehub.bills.model.BillAccount;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BillAccountRegisterDTO {

    @NotBlank(message = "Institution ID is required")
    private String billInstitutionId;
    @NotBlank(message = "User ID is required")
    private String userId;
    @NotBlank(message = "Bill Account Name is required")
    private String name;
    @NotBlank(message = "Bill Username is required")
    private String username;
    @NotBlank(message = "Bill Password is required")
    private String password;

    public BillAccount toEntity() {
        BillAccount billAccount = new BillAccount();
        billAccount.setBillInstitutionId(Long.valueOf(this.billInstitutionId));
        billAccount.setUserId(Long.valueOf(this.userId));
        billAccount.setName(this.name);
        billAccount.setUsername(this.username);
        billAccount.setPassword(this.password);
        billAccount.setActive(true); // New bill accounts are active by default

        return billAccount;
    }
}
