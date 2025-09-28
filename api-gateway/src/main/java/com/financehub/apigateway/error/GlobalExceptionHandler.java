package com.financehub.apigateway.error;

import com.financehub.core.error.ConflictException;
import com.financehub.core.error.NotFoundException;
import com.financehub.core.error.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorDTO> handleApiException(NotFoundException ex, HttpServletRequest request) {
        ApiErrorDTO error = ApiErrorDTO.of(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI(), ex.getDetails());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiErrorDTO> handleConflictException(ConflictException ex, HttpServletRequest request) {
        ApiErrorDTO error = ApiErrorDTO.of(HttpStatus.CONFLICT, ex.getMessage(), request.getRequestURI(), ex.getDetails());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> validationErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Invalid value",
                        (existing, replacement) -> existing // In case of duplicate keys, keep the existing message
                ));
        ApiErrorDTO error = ApiErrorDTO.of(HttpStatus.BAD_REQUEST, "Validation failed", request.getRequestURI(), validationErrors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErrorDTO> handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
        ApiErrorDTO error = ApiErrorDTO.of(HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getRequestURI(), ex.getDetails());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
