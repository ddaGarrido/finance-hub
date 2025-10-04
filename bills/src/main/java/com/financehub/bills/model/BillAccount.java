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
@Table(name = "bill_accounts", schema = "bills")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BillAccount extends DBEntity {

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false, length = 120)
    private String billAccountName;

    @Column(nullable = false, length = 120)
    private String billInstitutionName;

    @Column(nullable = false, length = 120)
    private String billUsername;

    @Column(nullable = false, length = 120)
    private String billPassword;

    @Column(nullable = false)
    private Boolean active;
}
