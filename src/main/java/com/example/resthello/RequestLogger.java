package com.example.resthello;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLogger {
    public enum LogLevel {
        INFO, WARN, ERROR
    }

    // Method without custom message
    public static void logRequest(Class<?> clazz, HttpServletRequest request, LogLevel level) {
        logRequest(clazz, request, level, null);
    }

    // Method with custom message
    public static void logRequest(Class<?> clazz, HttpServletRequest request, LogLevel level, String customMessage) {
        Logger logger = LoggerFactory.getLogger(clazz);
        String fullUrl = request.getRequestURL().toString()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        String message = "Request received: {} {} from {}";

        if (customMessage != null) {
            message += ". " + customMessage; // Append custom message if provided
        }

        switch (level) {
            case WARN:
                logger.warn(message, request.getMethod(), fullUrl, request.getRemoteHost());
                break;
            case ERROR:
                logger.error(message, request.getMethod(), fullUrl, request.getRemoteHost());
                break;
            case INFO:
            default:
                logger.info(message, request.getMethod(), fullUrl, request.getRemoteHost());
        }
    }
}
