package com.financehub.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.financehub")
@EnableJpaRepositories(basePackages = "com.financehub")
@EntityScan(basePackages = "com.financehub")
public class FinanceHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceHubApplication.class, args);
    }
}
