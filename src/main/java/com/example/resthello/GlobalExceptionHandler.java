package com.example.resthello;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import static com.example.resthello.RequestLogger.LogLevel.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private RequestLogger requestLogger;

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        ResponseEntity response = ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("{\"error\": \"Method " + ex.getMethod() + " not supported\"}");
        requestLogger.logRequest(this.getClass(), request, response, WARN, "Method '" +request.getMethod() + "' is not allowed!");
        return response;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingParams(MissingServletRequestParameterException ex, HttpServletRequest request) {
        String name = ex.getParameterName();
        ResponseEntity response = ResponseEntity.badRequest().body("{\"error\": \"Parameter '" + name + "' is missing\"}");
        requestLogger.logRequest(this.getClass(), request, response, WARN, "Parameter '" + name + "' is missing!");
        return response;
    }
}