package com.financehub.bills.connector;

import java.util.List;

//@Value
//@Builder
public record HealthCheckResult(
        boolean healthy,
        String initialUrl,
        String finalUrl,
        int statusCode,
        long elapsedMs,
        List<String> redirectChain,
        String note,                    // e.g., "Contains login form", "HEAD->GET fallback", errors, etc.
        String error) {

    public static class Builder {
        private boolean healthy;
        private String initialUrl;
        private String finalUrl;
        private int statusCode;
        private long elapsedMs;
        private List<String> redirectChain;
        private String note;                    // e.g., "Contains login form", "HEAD->GET fallback", errors, etc.
        private String error;

        public Builder healthy(boolean healthy) {
            this.healthy = healthy;
            return this;
        }

        public Builder initialUrl(String initialUrl) {
            this.initialUrl = initialUrl;
            return this;
        }

        public Builder finalUrl(String finalUrl) {
            this.finalUrl = finalUrl;
            return this;
        }

        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder elapsedMs(long elapsedMs) {
            this.elapsedMs = elapsedMs;
            return this;
        }

        public Builder redirectChain(List<String> redirectChain) {
            this.redirectChain = redirectChain;
            return this;
        }

        public Builder note(String note) {
            this.note = note;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public HealthCheckResult build() {
            return new HealthCheckResult(healthy, initialUrl, finalUrl, statusCode, elapsedMs, redirectChain, note, error);
        }
    }

    public static Builder builderInstance() {
        return new Builder();
    }
}