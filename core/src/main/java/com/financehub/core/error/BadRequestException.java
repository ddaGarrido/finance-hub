package com.financehub.core.error;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final Object details;

    public BadRequestException(String message) {
        super(message);
        this.details = null;
    }

    public BadRequestException(String message, Object details) {
        super(message);
        this.details = details;
    }
}
