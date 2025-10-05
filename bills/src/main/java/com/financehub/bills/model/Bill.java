package com.financehub.bills.model;

import com.financehub.core.model.DBEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "bills", schema = "bills")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Bill extends DBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bills_id_seq")
    @SequenceGenerator(name = "bills_id_seq", sequenceName = "bills.bills_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Long billInstitutionId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long billAccountId;

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

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(nullable = false, columnDefinition = "jsonb")
    private String tagsJson;

    @Column(nullable = false, columnDefinition = "jsonb")
    private String metadataJson;
}
