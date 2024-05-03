package com.example.resthello;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static com.example.resthello.RequestLogger.LogLevel.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        RequestLogger.logRequest(this.getClass(), request, WARN, "Method '" +request.getMethod() + "' is not allowed!");
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("{\"error\": \"Method " + ex.getMethod() + " not supported\"}");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String name = ex.getParameterName();
        RequestLogger.logRequest(this.getClass(), request, WARN, "Parameter '" + name + "' is missing!");
        return ResponseEntity.badRequest().body("{\"error\": \"Parameter '" + name + "' is missing\"}");
    }
}