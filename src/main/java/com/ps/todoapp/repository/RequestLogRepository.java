package com.ps.todoapp.repository;

import com.ps.todoapp.entity.requestlog.RequestLog;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestLogRepository extends ListCrudRepository<RequestLog, Long> {
    List<RequestLog> findRequestLogByMethod(String method);
    long countByMethod(String method);
}
