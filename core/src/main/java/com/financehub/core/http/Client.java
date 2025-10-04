package com.financehub.core.http;

import org.apache.http.Header;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class Client implements Closeable {

    private CloseableHttpClient httpClient;
    private boolean test;
    private boolean createdHttpClient;

    //<editor-fold desc="Builders">
    public Client() {
//        this.httpClient = HttpClients.createDefault();
        this.httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
                .build();
        this.test = false;
        this.createdHttpClient = true;
    }
    public Client(CloseableHttpClient httpClient) {
        this(httpClient, false);
    }
    public Client(Boolean test) {
        this(HttpClients.createDefault(), test);
    }
    public Client(CloseableHttpClient httpClient, Boolean test) {
        this.httpClient = httpClient;
        this.test = test;
        this.createdHttpClient = true;
    }
    //</editor-fold>

    @Override
    public void close() throws IOException {
        this.httpClient.close();
    }

    //<editor-fold desc="Execution Handlers">
    private Response executeHttpCall(HttpRequestBase httpRequest) throws IOException {
        try {
            CloseableHttpResponse response = this.httpClient.execute(httpRequest);
            return this.getResponse(response);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if (this.createdHttpClient && !this.test) {
                this.httpClient.close();
            }
        }
    }

    public Response getResponse(CloseableHttpResponse response) throws IOException {
        ResponseHandler<String> handler = new SendGridResponseHandler();
        String responseBody = handler.handleResponse(response);

        int statusCode = response.getStatusLine().getStatusCode();

        Header[] headers = response.getAllHeaders();
        Map<String, String> responseHeaders = new HashMap<>();
        for (Header h : headers) {
            responseHeaders.put(h.getName(), h.getValue());
        }

        return new Response(statusCode, responseBody, responseHeaders);
    }

    public URI buildUri(String baseUri, String endpoint, Map<String, String> queryParams) throws URISyntaxException {
        URIBuilder builder = new URIBuilder(baseUri);
        URI uri = null;

        builder.setPath(endpoint);

        if (queryParams != null) {
            String valueDelimiter = "&";

            queryParams.forEach((k, v) -> {
                if (v.contains(valueDelimiter)) {
                    String[] values = v.split(valueDelimiter);
                    for (String val : values) {
                        builder.addParameter(k, val);
                    }
                } else {
                    builder.addParameter(k, v);
                }
            });
        }

        try {
            uri = builder.build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uri;
    }
    //</editor-fold>

    //<editor-fold desc="Http Method handlers">
    public Response handle(Request request) throws IOException {
        URI uri = null;
        HttpRequestBase httpRequestBase = null;

        try {
            uri = buildUri(request.getBaseUri(), request.getEndpoint(), request.getQueryParams());
            String url = uri.toString();
            switch (request.getMethod()) {
                case GET -> httpRequestBase = new HttpGet(url);
                case POST -> httpRequestBase = new HttpPost(url);
                case PUT -> httpRequestBase = new HttpPut(url);
                case DELETE -> httpRequestBase = new HttpDelete(url);
                default -> {
                    throw new UnsupportedOperationException("HTTP method not supported: " + request.getMethod());
                }
            }
        } catch (Exception e) {
            throw new IOException("Invalid URL: " + (uri != null ? uri.toString() : "null"));
        }

        if (request.getHeaders() != null) {
            request.getHeaders().forEach(httpRequestBase::setHeader);
        }

        return executeHttpCall(httpRequestBase);
    }

    public Response get(Request request) throws IOException { return handle(request); }
    public Response post(Request request) throws IOException { return handle(request); }
    public Response put(Request request) throws IOException { return handle(request); }
    public Response delete(Request request) throws IOException { return handle(request); }
    //</editor-fold>

    // Generic method to execute any HTTP request
    public Response send(Request request) throws IOException {
        return handle(request);
//        switch (request.getMethod()) {
//            case GET -> { return get(request); }
//            case POST -> { return post(request); }
//            case PUT ->  { return put(request); }
//            case DELETE -> { return delete(request); }
//            default ->
//                throw new UnsupportedOperationException("HTTP method not supported: " + request.getMethod());
//
//        }
    }
}
