package com.ps.todoapp.controller.requestlog;

import com.ps.todoapp.entity.requestlog.RequestLog;
import com.ps.todoapp.service.requestLog.RequestLogService;
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
    private RequestLogService requestLogService;

    @GetMapping()
    public List<RequestLog> getAll(@RequestParam(required = false) String method) {
        return requestLogService.getRequestLogs(method);
    }

    @GetMapping("/count")
    public long getLogs(@RequestParam(required = false) String method) {
        return requestLogService.getRequestsCount(method);
    }
}
