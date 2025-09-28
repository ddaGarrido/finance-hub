package com.financehub.core.error;

import lombok.Getter;

import java.util.Map;

@Getter
public class UnauthorizedException extends RuntimeException {
    private final transient Map<String, Object> details;

    public UnauthorizedException(String message) {
        super(message);
        this.details = null;
    }

    public UnauthorizedException(String message, Map<String, Object> details) {
        super(message);
        this.details = details;
    }
}
