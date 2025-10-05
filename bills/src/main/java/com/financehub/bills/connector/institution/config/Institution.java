package com.financehub.bills.connector.institution.config;

import com.financehub.bills.connector.HealthCheckResult;
import com.financehub.core.http.Http;

import java.net.URI;

public interface Institution {

    String getId();
    String getDisplayName();

    URI getWebsiteUrl();
    URI getLoginUrl();

    HealthCheckResult checkWebsite(Http http);
    HealthCheckResult checkLogin(Http http);
}
