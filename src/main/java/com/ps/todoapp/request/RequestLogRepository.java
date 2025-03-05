package com.ps.todoapp.request;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RequestLogRepository extends ListCrudRepository<RequestLog, Long> {
    List<RequestLog> findRequestLogByMethod(String method);
    long countByMethod(String method);
}
