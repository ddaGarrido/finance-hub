package com.financehub.bills.connector;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InstitutionCheckReport {
    String institutionId;
    String displayName;

    HealthCheckResult websiteCheck;
    HealthCheckResult loginCheck;

    public boolean allHealthy() {
        return websiteCheck.healthy() && loginCheck.healthy();
    }
}
