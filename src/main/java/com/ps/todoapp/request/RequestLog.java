package com.ps.todoapp.request;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Represents a log entry for an HTTP request.
 * <p>
 * This is a single record that stores information about an HTTP request, including the HTTP method used,
 * the request path, and the timestamp when the request was made.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLog {

    @Id
    @GeneratedValue
    private long id;

    private String method;
    private String path;
    private Instant timestamp;

    public RequestLog(String method, String path, Instant timestamp) {
        this.method = method;
        this.path = path;
        this.timestamp = timestamp;
    }
}
