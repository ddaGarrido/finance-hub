package com.financehub.bills.dto.billinstitution;

import com.financehub.bills.model.BillInstitution;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class BillInstitutionResponseDTO {

    private String id;
    private String providerKey;
    private String displayName;
    private String category;
    private String websiteUrl;
    private String loginUrl;
    private Boolean supportsWebhook;
    private Boolean active;
    private Object metadataJson;

    public BillInstitutionResponseDTO(BillInstitution billInstitution) {
        this.id = billInstitution.getId().toString();
        this.providerKey = billInstitution.getProviderKey();
        this.displayName = billInstitution.getDisplayName();
        this.category = billInstitution.getCategory();
        this.websiteUrl = billInstitution.getWebsiteUrl();
        this.loginUrl = billInstitution.getLoginUrl();
        this.supportsWebhook = billInstitution.getSupportsWebhook();
        this.active = billInstitution.getActive();
        this.metadataJson = billInstitution.getMetadataJson();
    }
}
