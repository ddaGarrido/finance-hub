package com.financehub.core.http;

import org.apache.commons.lang3.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

@AllArgsConstructor
@Getter
public class Request {
    private HttpMethod method;
    private String baseUri; // Maybe change to host
    private String endpoint;
    private String target; // full URL (baseUri + endpoint)
    private String body;
    private Map<String, String> headers;
    private Map<String, String> queryParams;

    public Request() {
        this.headers = new LinkedHashMap<>();
        this.queryParams = new LinkedHashMap<>();
        this.reset();
    }

    public void reset() {
        this.clearMethod();
        this.clearBaseUri();
        this.clearEndpoint();
        this.clearBody();
        this.clearHeaders();
        this.clearQueryParams();
    }

    // Methods
    public Request method(HttpMethod method) {
        this.method = method;
        return this;
    }

    // URL
    public Request baseUri(String baseUri) {
        this.baseUri = baseUri;
        return this;
    }
    public Request endpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }
    public Request target(String target) {
        this.target = target;
        return this;
    }
    public String url() {
        if (StringUtils.isNotEmpty(this.target)) {
            return this.target;
        }
        String base = retrieveValidated(this.baseUri);
        String end = retrieveValidated(this.endpoint);

        return String.format("%s%s", base, end);
    }

    // Body
    public Request body(String body) {
        this.body = body;
        return this;
    }

    // Headers
    // Insert methods
    public Request header(String name, String value) {
        this.headers.put(name, value);
        return this;
    }
    public Request headers(Map<String, String> headers) {
        headers.forEach(this::header);
        return this;
    }
    // Access methods
    public String header(String name) {
        return headers.get(name);
    }
    // Remove methods
    public Request removeHeader(String name) {
        this.headers.remove(name);
        return this;
    }
    public Request removeHeader(String...names) {
        Arrays.stream(names).forEach(this::removeHeader);
        return this;
    }

    // Query parameters
    // Insert methods
    public Request queryParam(String name, String value) {
        this.queryParams.computeIfAbsent(name, k -> value);
        return this;
    }
    // Access methods
    public String queryParam(String name) {
        return this.queryParams.get(name);
    }
    public Map<String, String> queryParams() {
        return this.queryParams;
    }
    // Remove methods
    public Request removeQueryParam(String name) {
        this.queryParams.remove(name);
        return this;
    }
    public Request removeQueryParam(String...names) {
        Arrays.stream(names).forEach(this::removeQueryParam);
        return this;
    }

    // Clear all
    public void clearMethod() { this.method = null; }
    public void clearBaseUri() { this.baseUri = ""; }
    public void clearEndpoint() { this.endpoint = ""; }
    public void clearTarget() { this.target = ""; }
    public void clearBody() { this.body = ""; }
    public void clearHeaders() { this.headers.clear(); }
    public void clearQueryParams() { this.queryParams.clear(); }

    // Body
//    public Request body(String body, String contentType) {
//        if (body != null) {
//            this.body = body.getBytes();
//            this.contentType = contentType;
//
//        }
//        return this;
//    }
//    public Request json(String json) {
//        return body(json, MediaType.APPLICATION_JSON_VALUE);
//    }

    // Query parameters
//    public Request query(String name, String value) {
//        if (name != null && value != null) {
//            query.computeIfAbsent(name, k -> new ArrayList<>()).add(value);
//        }
//        return this;
//    }
//    public Request query(Map<String, String> params) {
//        if (params != null) {
//            params.forEach(this::query);
//        }
//        return this;
//    }
//    private static URI withQuery(URI base, Map<String, List<String>> query) {
//        if (query.isEmpty()) return base;
//        String existing = base.getQuery();
//        String add = query.entrySet().stream()
//                .flatMap(e -> e.getValue().stream().map(v -> encode(e.getKey()) + "=" + encode(v)))
//                .collect(Collectors.joining("&"));
//        String combined = (existing == null || existing.isEmpty()) ? add : existing + "&" + add;
//
//        try {
//            return new URI(base.getScheme(), base.getAuthority(), base.getPath(), combined, base.getFragment());
//        } catch (Exception e) {
//            throw new IllegalArgumentException("Invalid URI when applying query params", e);
//        }
//    }
//
//    // Form
//    public Request form(Map<String, ?> formData) {
//        String encoded = encodeForm(formData);
//        return body(encoded, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
//    }

    // Send
//    public Response send() {
//        try {
//            var start = System.nanoTime();
//            var client = (redirectOverride == null)
//                    ? http.client() : HttpClient.newBuilder()
//                    .followRedirects(redirectOverride)
//                    .connectTimeout(getConnectTimeout())
//                    .build();
//
//            var reqBuilder = HttpRequest.newBuilder()
//                    .uri(resolveUri())
//                    .timeout(timeoutOverride != null ? timeoutOverride : http.defaultTimeout());
//
//            // headers
//            headers.forEach(reqBuilder::header);
//            if (contentType != null && !headers.containsKey("Content-Type")) {
//                reqBuilder.header("Content-Type", contentType);
//            }
//
//            // method & body
//            if (body != null) {
//                reqBuilder.method(method, BodyPublishers.ofByteArray(body));
//            } else if (method.equals("GET") || method.equals("DELETE") || method.equals("HEAD")) {
//                reqBuilder.method(method, BodyPublishers.noBody());
//            } else {
//                reqBuilder.method(method, BodyPublishers.noBody());
//            }
//
//            // execute
//            var jdkResponse = client.send(reqBuilder.build(), HttpResponse.BodyHandlers.ofByteArray());
//            var elapsed = (System.nanoTime() - start) / 1_000_000;
//
//            return Response.fromJdkHttp(jdkResponse, elapsed);
//        } catch (HttpTimeoutException e) {
//            throw new RuntimeException("Request timed out", e);
//        } catch (Exception e) {
//            throw new RuntimeException("HTTP request failed", e);
//        }
//    }
//
//    private Duration getConnectTimeout() {
//        return http.defaultTimeout();
//    }
//
//    // Helpers
//    private static String encode(String s) {
//        try {
//            return URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private static String encodeForm(Map<String, ?> fields) {
//        if (fields == null || fields.isEmpty()) return "";
//        return fields.entrySet().stream()
//                .map(e -> encode(e.getKey()) + "=" + encode(e.getValue() == null ? "" : String.valueOf(e.getValue())))
//                .collect(Collectors.joining("&"));
//    }
//    private URI resolveUri() {
//        URI uri = URI.create(target);
//        if (uri.isAbsolute()) {
//            return uri;
//        }
//        if (http.baseUri() != null) {
//            URI joined = http.baseUri().resolve(stripLeadingSlash(target));
//            return http.baseUri().resolve(uri);
//        } else {
//            throw new IllegalStateException("Cannot resolve relative URL without a base URL");
//        }
//    }
//    private static String stripLeadingSlash(String s) {
//        return s.startsWith("/") ? s.substring(1) : s;
//    }

    // Helper
    private static String retrieveValidated(String s) {
        return StringUtils.isEmpty(s) ? "" : s;
    }
}
