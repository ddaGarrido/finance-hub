package com.financehub.core.http;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

public final class Http {

    private final Client client;
    private final String baseUrl;

    public Http() {
        this(new Client());
    }

    public Http(Client client) {
        this(client, null);
    }

    public Http(Client client, String baseUrl) {
        this.client = client;
        this.baseUrl = baseUrl;
    }

    public Call get(URI endpointOrUrl) { return get(String.valueOf(endpointOrUrl)); }
    public Call get(String endpointOrUrl) { return new Call(HttpMethod.GET, endpointOrUrl); }
    public Call post(String endpointOrUrl) { return new Call(HttpMethod.POST, endpointOrUrl); }

    public final class Call {
        private final Request req;

        private Call(HttpMethod method, String endpointOrUrl) {
            this.req = new Request()
                .method(method)
                .baseUri(resolveBase(endpointOrUrl).base)
                .endpoint(resolveBase(endpointOrUrl).endpoint);
        }

        public Call header(String k, String v) {
            req.header(k, v);
            return this;
        }

        public Call headers(Map<String, String> hs) {
            if (hs != null) hs.forEach(req::header);
            return this;
        }

        public Call query(String k, String v) {
            req.queryParam(k, v);
            return this;
        }

        public Call queries(Map<String, String> qp) {
            if (qp != null) qp.forEach(req::queryParam);
            return this;
        }

        /** Raw body – caller sets Content-Type header if needed. */
        public Call body(String body) {
            req.body(body);
            return this;
        }

//        /** x-www-form-urlencoded */
//        public Call form(Map<String, ?> fields) {
//            req.form(fields);
//            return this;
//        }
//
//        /** JSON string (sets Content-Type) */
//        public Call json(String json) {
//            req.json(json);
//            return this;
//        }

        public Response send() throws IOException {
            return client.send(req);
        }

        public String url() {
            return req.url();
        }
    }

    // ---------- URL resolver (prevents double "https://https://") ----------
    private Resolved resolveBase(String endpointOrUrl) {
        Objects.requireNonNull(endpointOrUrl, "endpointOrUrl");
        String trimmed = endpointOrUrl.trim();

        // Absolute? use as-is, ignore configured baseUrl
        if (isAbsolute(trimmed)) {
            URI u = URI.create(trimmed);
            return new Resolved(u.getScheme() + "://" + u.getHost() + (u.getPort() > 0 ? (":" + u.getPort()) : ""), u.getRawPath());
        }

        if (baseUrl == null || baseUrl.isBlank()) {
            // treat endpoint as absolute base and empty path; your Request will join them
            return new Resolved(trimmed, "/");
        }

        String base = ensureNoTrailingSlash(baseUrl);
        String ep = ensureLeadingSlash(trimmed);
        return new Resolved(base, ep);
    }

    private static boolean isAbsolute(String s) {
        try { return URI.create(s).isAbsolute(); } catch (Exception e) { return false; }
    }
    private static String ensureNoTrailingSlash(String s) {
        return (s.endsWith("/")) ? s.substring(0, s.length()-1) : s;
    }
    private static String ensureLeadingSlash(String s) {
        return (s.startsWith("/")) ? s : "/" + s;
    }

    private record Resolved(String base, String endpoint) {}
}
