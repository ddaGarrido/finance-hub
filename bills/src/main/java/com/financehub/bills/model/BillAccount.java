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
@Table(name = "bill_accounts", schema = "bills")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class BillAccount extends DBEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_accounts_id_seq")
    @SequenceGenerator(name = "bill_accounts_id_seq", sequenceName = "bills.bill_accounts_id_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Long billInstitutionId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, length = 120)
    private String username;

    @Column(nullable = false, length = 120)
    private String password;

    @Column(nullable = false)
    private Boolean active;
}
