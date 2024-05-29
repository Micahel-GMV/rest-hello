package com.example.resthello.db.log;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "incoming_requests")
public class DbLogRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loglevel;

    @Column(nullable = false)
    private Timestamp timestamp;

    @Column(nullable = false)
    private String method;

    @Column(name = "request_url", nullable = false)
    private String requestUrl;

    @Column(name = "query_string")
    private String queryString;

    @Column(name = "parameters_json")
    private String parametersJson;

    @Column(name = "endpoint")
    private String endpoint;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "response_body")
    private String responseBody;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoglevel() {
        return loglevel;
    }

    public void setLoglevel(String loglevel) {
        this.loglevel = loglevel;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getParametersJson() {
        return parametersJson;
    }

    public void setParametersJson(String parametersJson) {
        this.parametersJson = parametersJson;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
