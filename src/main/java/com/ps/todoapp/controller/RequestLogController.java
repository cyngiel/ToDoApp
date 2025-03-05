package com.ps.todoapp.controller;

import com.ps.todoapp.entity.RequestLog;
import com.ps.todoapp.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class RequestLogController {

    @Autowired
    private RequestLogRepository repository;

    @GetMapping("")
    public List<RequestLog> getAll(@RequestParam(required = false) String method) {
        if (method == null || method.isEmpty()) {
            return repository.findAll();
        }

        return repository.findRequestLogByMethod(method);
    }

    @GetMapping("/count")
    public long getLogs(@RequestParam(required = false) String method) {
        if (method == null || method.isEmpty()) {
            return repository.count();
        }

        return repository.countByMethod(method);
    }
}
