package com.financehub.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.financehub.apigateway",
        "com.financehub.core",
        "com.financehub.usermanagement",
        "com.financehub.bankaccounts",
        "com.financehub.bills"
})
@EntityScan(basePackages = {
        "com.financehub.core.model",
        "com.financehub.bills.dto",
})
@EnableJpaRepositories(basePackages = {
        "com.financehub.core.repository",
        "com.financehub.usermanagement.repository",
        "com.financehub.bankaccounts.repository",
        "com.financehub.bills.repository"
})
public class FinanceHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceHubApplication.class, args);
    }
}
