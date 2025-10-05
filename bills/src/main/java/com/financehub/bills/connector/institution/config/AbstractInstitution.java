package com.financehub.bills.connector.institution.config;

import com.financehub.bills.connector.HealthCheckResult;
import com.financehub.core.http.Http;
import com.financehub.core.http.Response;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;

@NoArgsConstructor
public abstract class AbstractInstitution implements Institution {

    // ---------- defaults (used unless overridden by Config) ----------
    private String id = "semantic-ui";
    private String displayName = "Semantic UI";
    private URI websiteUrl = URI.create("https://semantic-ui.com");
    private String loginPath = "/examples/login.html";
    private URI loginUrl = websiteUrl.resolve(loginPath);

    protected AbstractInstitution(Config cfg) {
        if (cfg == null) return;

        if (StringUtils.isNotBlank(cfg.id()))          id = cfg.id();
        if (StringUtils.isNotBlank(cfg.displayName())) displayName = cfg.displayName();
        if (StringUtils.isNotBlank(cfg.baseUrl()))     websiteUrl = URI.create(cfg.baseUrl());
        if (StringUtils.isNotBlank(cfg.loginPath()))   loginPath = cfg.loginPath();

        if (StringUtils.isNotBlank(cfg.loginUrl())) {
            this.loginUrl = URI.create(cfg.loginUrl());
        } else {
            // recompute from (possibly updated) base + path
            this.loginUrl = this.websiteUrl.resolve(this.loginPath);
        }
    }

    public record Config(String id, String displayName, String baseUrl, String loginPath, String loginUrl) {}

    // ---------- Institution getters ----------
    @Override public String getId() { return id; }
    @Override public String getDisplayName() { return displayName; }
    @Override public URI getWebsiteUrl() { return websiteUrl; }
    @Override public URI getLoginUrl() { return loginUrl; }

    // ---------- load method to initialize institution ----------
    @Override
    public HealthCheckResult checkWebsite(Http http) {
        try {
            Response response = http.get(websiteUrl).send();
            int status = response.getStatusCode();

            if (status != 200) {
                String note = "Website returned status code: " + status;
                return createResult(false, websiteUrl, status, 0, note);
            } else {
                String title = response.getBody().split("<title>")[1].split("</title>")[0];
                String note = "Website is reachable - Title: " + title;
                return createResult(true, websiteUrl, status, 0, note);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HealthCheckResult checkLogin(Http http) {
        try {
            Response response = http.get(loginUrl).send();
            int status = response.getStatusCode();
            String note = "";

            if (status != 200) {
                note = "Website returned status code: " + status;
                return createResult(false, loginUrl, status, 0, note);
            } else {
                Element el = response.html().select("form[class=ui large form]").first();

                if (el == null) {
                    note = "Login form not detected";
                    return createResult(false, loginUrl, status, 0, note);
                }
                note = "Login form detected - Tags found: " + response.html().select("form").size();
                return createResult(true, loginUrl, status, 0, note);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HealthCheckResult createResult(boolean healthy, URI initialUrl, int status, long elapsedTime, String note) {
        return HealthCheckResult.builderInstance()
            .healthy(healthy)
            .initialUrl(String.valueOf(initialUrl))
            .finalUrl("")
            .statusCode(status)
            .elapsedMs(elapsedTime)
            .redirectChain(null)
            .note(note)
            .error(null)
            .build();
    }
}
