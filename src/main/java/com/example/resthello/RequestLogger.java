package com.example.resthello;

import com.example.resthello.db.log.DbLogRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import java.awt.*;

@Component
public class RequestLogger {

    @Autowired
    private DbLogRequestService dbLogRequestService;

    public enum LogLevel {
        INFO, WARN, ERROR
    }

    // Method without custom message
    public void logRequest(Class<?> clazz, HttpServletRequest request, ResponseEntity response, LogLevel level) {
        logRequest(clazz, request, response, level, null);
    }

    // Method with custom message
    public void logRequest(Class<?> clazz, HttpServletRequest request, ResponseEntity response, LogLevel level, String customMessage) {
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
                Toolkit.getDefaultToolkit().beep();
                Toolkit.getDefaultToolkit().beep();
                break;
            case ERROR:
                logger.error(message, request.getMethod(), fullUrl, request.getRemoteHost());
                Toolkit.getDefaultToolkit().beep();
                Toolkit.getDefaultToolkit().beep();
                Toolkit.getDefaultToolkit().beep();
                break;
            case INFO:
            default:
                logger.info(message, request.getMethod(), fullUrl, request.getRemoteHost());
                Toolkit.getDefaultToolkit().beep();
        }
        dbLogRequestService.logRequest(request, response, level.name());
    }
}
