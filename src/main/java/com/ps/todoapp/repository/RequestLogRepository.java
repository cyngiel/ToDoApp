package com.ps.todoapp.repository;

import com.ps.todoapp.entity.RequestLog;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RequestLogRepository extends ListCrudRepository<RequestLog, Long> {
    List<RequestLog> findRequestLogByMethod(String method);
    long countByMethod(String method);
}
