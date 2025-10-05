package com.financehub.bills.model;

import com.financehub.core.model.DBEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill_institutions", schema = "bills")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BillInstitution extends DBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_institutions_id_seq")
    @SequenceGenerator(name = "bill_institutions_id_seq", sequenceName = "bills.bill_institutions_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 80)
    private String institutionKey;

    @Column(nullable = false, length = 120)
    private String institutionName;

    @Column(nullable = false, length = 32)
    private String category;

    @Column(nullable = false)
    private String websiteUrl;

    @Column(nullable = false)
    private String loginUrl;

    @Column(nullable = false)
    private Boolean supportsWebhook;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false, columnDefinition = "jsonb")
    private String metadataJson;
}
