package com.financehub.apigateway.config;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.UUID;

@Configuration
public class WebFiltersConfig {

    private static class CorrelationIdFilter implements Filter {
        private static final String HEADER = "X-Correlation-Id";
        private static final String MDC_KEY = "traceId";

        @Override
        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest http = (HttpServletRequest) req;
            HttpServletResponse resp = (HttpServletResponse) res;

            String id = http.getHeader(HEADER);

            if (StringUtils.isBlank(id)) {
                id = UUID.randomUUID().toString();
            }

            MDC.put(MDC_KEY, id);
            try {
                resp.setHeader(HEADER, id);
                chain.doFilter(req, res);
            } finally {
                MDC.remove(MDC_KEY);
            }
        }

        @Bean
        public FilterRegistrationBean<CorrelationIdFilter> correlationIdFilter() {
            var bean = new FilterRegistrationBean<>(new CorrelationIdFilter());
            bean.setOrder(1);
            return bean;
        }
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> commonTags() {
        return registry -> registry.config().commonTags(//"commonTagForTest"
//                "application123", "financehub-api-gateway1234", "test", "httpfilterttt"
        );
    }
}
