package com.financehub.bills.connector.institution.config;

import com.financehub.bills.connector.institution.Demo;
import com.financehub.bills.connector.institution.PredialNet;
import com.financehub.bills.model.BillInstitution;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InstitutionFactory {

    public static Institution from(BillInstitution bi) {
        String id = bi.getId().toString();

        return switch (id) {
            case "demo" -> new Demo();
            case "predialnet" -> new PredialNet();
            default -> throw new IllegalArgumentException("Unknown institution id: " + id);
        };
    }
}
