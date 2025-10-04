package com.financehub.bills.model;

import com.financehub.core.model.DBEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "bill_institutions", schema = "bills")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BillInstitution extends DBEntity {

    @Column(nullable = false, length = 80)
    private String providerKey;

    @Column(nullable = false, length = 120)
    private String displayName;

    @Column(nullable = false, length = 32)
    private String category;

    @Column(nullable = false, length = 255)
    private String websiteUrl;

    @Column(nullable = false, length = 255)
    private String loginUrl;

    @Column(nullable = false)
    private Boolean supportsWebhook;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false, columnDefinition = "jsonb")
    private String metadataJson;
}
