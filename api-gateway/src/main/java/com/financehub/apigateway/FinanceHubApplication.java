package com.financehub.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {
        "com.financehub.apigateway",
        "com.financehub.core",
        "com.financehub.usermanagement",
        "com.financehub.bankaccounts",
})
@EntityScan(basePackages = {"com.financehub.core.model"})
public class FinanceHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceHubApplication.class, args);
    }
}
