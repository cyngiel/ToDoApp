package com.ps.todoapp.service.requestLog;

import com.ps.todoapp.entity.requestlog.RequestLog;
import com.ps.todoapp.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestLogService {

    @Autowired
    RequestLogRepository repository;

    public List<RequestLog> getRequestLogs(String method) {
        if (method == null || method.isEmpty()) {
            return repository.findAll();
        }

        return repository.findRequestLogByMethod(method);
    }

    public long getRequestsCount(String method) {
        if (method == null || method.isEmpty()) {
            return repository.count();
        }

        return repository.countByMethod(method);
    }
}
