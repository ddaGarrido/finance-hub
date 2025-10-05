package com.financehub.bills.connector.institution;

import com.financehub.bills.connector.institution.config.AbstractInstitution;
import com.financehub.bills.connector.HealthCheckResult;
import com.financehub.core.error.BadRequestException;
import com.financehub.core.http.Http;
import com.financehub.core.http.Response;

import java.io.IOException;

public class PredialNet extends AbstractInstitution {

    private static final String ID = "predialnet";
    private static final String DISPLAY_NAME = "PredialNet";
    private static final String BASE_URL = "https://www.predialnet.com.br/";
    private static final String LOGIN_URL = "https://minhaconta.predialnet.com.br/";

    public PredialNet() {
        super(new Config(
                ID,
                DISPLAY_NAME,
                BASE_URL,
                null,
                LOGIN_URL
        ));
    }

    @Override public HealthCheckResult checkWebsite(Http http) {
        long start = System.nanoTime();
        try {
            Response response = http.get(getWebsiteUrl().toString()).send();
            long duration = System.nanoTime() - start;
            if (response.getStatusCode() == 200) {
                String note = "Website is reachable - Title: " + response.html().select("title").first().text();
                return createResult(true, getWebsiteUrl(), response.getStatusCode(), 0, note);
            } else {
                String note = "Website returned status code: " + response.getStatusCode();
                return createResult(false, getWebsiteUrl(), response.getStatusCode(), 0, note);
            }
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    @Override public HealthCheckResult checkLogin(Http http) {
        long start = System.nanoTime();
        try {
            Response response = http.get(getLoginUrl().toString()).send();
            long duration = System.nanoTime() - start;
            if (response.getStatusCode() == 200) {
                String passTitle = response.html().select("input[name=cpf]").first().attr("title");
                String note = "Website is reachable - Password Title: " + passTitle;
                return createResult(true, getLoginUrl(), response.getStatusCode(), 0, note);
            } else {
                String note = "Website returned status code: " + response.getStatusCode();
                return createResult(false, getLoginUrl(), response.getStatusCode(), 0, note);
            }
        } catch (IOException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
