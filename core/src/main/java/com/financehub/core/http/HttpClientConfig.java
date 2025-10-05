package com.financehub.core.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class HttpClientConfig {

    @Bean(destroyMethod = "shutdown" )
    public PoolingHttpClientConnectionManager connectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(60, TimeUnit.SECONDS);
        connectionManager.setMaxTotal(200); // Maximum total connections
        connectionManager.setDefaultMaxPerRoute(50); // Maximum connections per route
        return connectionManager;
    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(15_000) // Connection timeout
                .setSocketTimeout(20_000)  // Socket timeout
                .setConnectionRequestTimeout(15_000) // Connection request timeout
                .build();
    }

    @Bean(destroyMethod = "close")
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager cm, RequestConfig rc) {
        return HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(rc)
                .evictExpiredConnections()
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries() // enable if you want, but be explicit
                .build();
    }
}
