package com.financehub.bills.service;

import com.financehub.bills.connector.HealthCheckResult;
import com.financehub.bills.connector.institution.config.Institution;
import com.financehub.bills.connector.InstitutionCheckReport;
import com.financehub.bills.connector.institution.config.InstitutionFactory;
import com.financehub.bills.model.BillInstitution;
import com.financehub.bills.repository.BillInstitutionRepository;
import com.financehub.core.error.NotFoundException;
import com.financehub.core.http.Http;
import com.financehub.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BillInstitutionService {

    private final BillInstitutionRepository billInstitutionRepository;
    private final UserRepository userRepository;

    public List<InstitutionCheckReport> healthCheckAllActive() {
        List<BillInstitution> institutions = billInstitutionRepository.findAllByActiveTrue();

        return institutions.stream().map(this::buildReport).toList();
    }
    public InstitutionCheckReport institutionCheckReport(String idOrKey) {
        BillInstitution bi = getBillInstitutionByIdOrKey(idOrKey);

        return buildReport(bi);
    }

    private InstitutionCheckReport buildReport(BillInstitution bi) {
        Institution inst = InstitutionFactory.from(bi);
        Http http = new Http();

        HealthCheckResult site  = inst.checkWebsite(http);
        HealthCheckResult login = inst.checkLogin(http);

        return InstitutionCheckReport.builder()
            .institutionId(inst.getId())
            .displayName(inst.getDisplayName())
            .websiteCheck(site)
            .loginCheck(login)
            .build();
    }

    public List<BillInstitution> listAllBillInstitutions() {
        return billInstitutionRepository.findAll();
    }

    public BillInstitution getBillInstitutionByIdOrKey(String id) {
        Optional<BillInstitution> bi;

        bi = Optional.ofNullable(billInstitutionRepository.findByInstitutionKey(id));
        if (bi.isPresent()) return bi.get();
        bi = billInstitutionRepository.findById(Long.valueOf(id));
        if (bi.isPresent()) return bi.get();

        throw new NotFoundException("Bill institution not found");
    }
}
