package com.financehub.apigateway.controller;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@AllArgsConstructor
public class HealthCheckController {

    private static final Logger log = LoggerFactory.getLogger(HealthCheckController.class);
    private final MeterRegistry meterRegistry;

    private static final Random RANDOM = new Random();
    private static final List<HttpStatus> RANDOM_STATUSES = List.of(
            // 2xx Success
            HttpStatus.OK, HttpStatus.CREATED, HttpStatus.ACCEPTED,
            // 3xx Redirection
            HttpStatus.MOVED_PERMANENTLY, HttpStatus.FOUND, HttpStatus.NOT_MODIFIED,
            // 4xx Client Error
            HttpStatus.BAD_REQUEST, HttpStatus.UNAUTHORIZED, HttpStatus.NOT_FOUND, HttpStatus.CONFLICT,
            // 5xx Server Error
            HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.BAD_GATEWAY, HttpStatus.SERVICE_UNAVAILABLE
    );

    @GetMapping("/api/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        return generateRandomResponse("Health");
    }

    @GetMapping("/api/tester")
    public ResponseEntity<Map<String, String>> tester() {
        return generateRandomResponse("Tester");
    }

    private ResponseEntity<Map<String, String>> generateRandomResponse(String endpointName) {
        // Select a random HttpStatus from our predefined list.
        HttpStatus randomStatus = RANDOM_STATUSES.get(RANDOM.nextInt(RANDOM_STATUSES.size()));

        log.info("{} check requested", endpointName);
        log.info("{} check result: {}", endpointName, randomStatus);

//        // Increment general API and specific endpoint counters.
//        meterRegistry.counter("C_Api").increment();
//        meterRegistry.counter("C_Api_" + endpointName).increment();

        // Determine the status code family (2xx, 3xx, etc.) and increment the correct counter.
        String statusCodeFamily;
        if (randomStatus.is2xxSuccessful()) {
            statusCodeFamily = "2xx";
        } else if (randomStatus.is3xxRedirection()) {
            statusCodeFamily = "3xx";
        } else if (randomStatus.is4xxClientError()) {
            statusCodeFamily = "4xx";
        } else if (randomStatus.is5xxServerError()) {
            statusCodeFamily = "5xx";
        } else {
            statusCodeFamily = "other";
        }

//        meterRegistry.counter("C_Api_" + endpointName + "_" + statusCodeFamily).increment();

        // Build and return the ResponseEntity with the random status.
        // The body includes the status reason for context.
        return new ResponseEntity<>(Map.of("status", randomStatus.getReasonPhrase()), randomStatus);
    }
}
