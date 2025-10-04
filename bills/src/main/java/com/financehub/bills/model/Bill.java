package com.financehub.bills.model;

import com.financehub.core.model.DBEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "bills", schema = "bills")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Bill extends DBEntity {

    @Column(nullable = false)
    private UUID userId;

//    @Column(nullable = false)
    private UUID accountId;

    @Column(nullable = false)
    private UUID institutionId;

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false, columnDefinition = "date")
    private Date dueDate;

    @Column(nullable = false, columnDefinition = "TIMESTAMPTZ")
    private ZonedDateTime paidAt;

    @Column(nullable = false, length = 64)
    private String barcode;

    @Column(nullable = false, length = 120)
    private String beneficiaryName;

    @Column(nullable = false, length = 20)
    private String beneficiaryTaxId;

    @Column(nullable = false, length = 20)
    private String payerTaxId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false, columnDefinition = "jsonb")
    private String tagsJson;

    @Column(nullable = false, columnDefinition = "jsonb")
    private String metadataJson;
}
