package com.example.resthello.db.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Service
public class DbLogRequestService {

    @Autowired
    private DbLogRequestRepository incomingRequestRepository;

    @Transactional
    public void logRequest(HttpServletRequest request, ResponseEntity response, String logLevel) {
        DbLogRequestEntity dbLogRequestEntity = new DbLogRequestEntity();
        dbLogRequestEntity.setLoglevel(logLevel);
        dbLogRequestEntity.setTimestamp(new Timestamp(System.currentTimeMillis()));
        dbLogRequestEntity.setMethod(request.getMethod());
        dbLogRequestEntity.setRequestUrl(request.getRequestURL().toString());
        dbLogRequestEntity.setQueryString(request.getQueryString());
        dbLogRequestEntity.setParametersJson(getParameterMapAsJson(request));
        dbLogRequestEntity.setEndpoint(getEndpoint(request));
        dbLogRequestEntity.setResponseCode(response.getStatusCode().value());
        dbLogRequestEntity.setResponseBody(response.hasBody() ? response.getBody().toString() : null);

        try {
            dbLogRequestEntity.setBody(getBody(request));
        } catch (IOException e) {
            System.out.println("Can`t get request body.");
            e.printStackTrace();
        }

        incomingRequestRepository.save(dbLogRequestEntity);
    }

    private String getParameterMapAsJson(HttpServletRequest request) {
        // Convert parameter map to JSON string
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> jsonMap = new HashMap<>();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (values.length == 1) {
                jsonMap.put(key, values[0]);
            } else {
                jsonMap.put(key, values);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(jsonMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

    private String getEndpoint(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        return requestURI.substring(contextPath.length());
    }

    public String getBody(HttpServletRequest request) throws IOException {
        StringBuilder body = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line).append("\n");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return body.toString();
    }
}