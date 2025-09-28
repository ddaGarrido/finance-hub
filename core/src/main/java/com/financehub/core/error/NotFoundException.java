package com.financehub.core.error;

import lombok.Getter;

import java.util.Map;

@Getter
public class NotFoundException extends RuntimeException {
    private final transient Map<String, Object> details;

    public NotFoundException(String message) {
        super(message);
        this.details = null;
    }

    public NotFoundException(String message, Map<String, Object> details) {
        super(message);
        this.details = details;
    }
}
