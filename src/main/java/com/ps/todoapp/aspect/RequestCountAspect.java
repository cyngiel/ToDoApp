package com.ps.todoapp.aspect;

import com.ps.todoapp.controller.task.TaskController;
import com.ps.todoapp.entity.requestlog.RequestLog;
import com.ps.todoapp.repository.RequestLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * Aspect responsible for logging HTTP requests to task-related controller methods.
 * <p>
 * This aspect intercepts all methods in {@link TaskController} and logs them into the database.
 */
@Aspect
@Component
public class RequestCountAspect {

    @Autowired
    private RequestLogRepository repository;

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* com.ps.todoapp.controller.task.TaskController.*(..))")
    public void taskControllerMethods() {}

    @After("taskControllerMethods() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void afterGetRequests() {
        repository.save(new RequestLog(request.getMethod(), request.getRequestURI(), Instant.now()));
    }

    @After("taskControllerMethods() && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void afterPostRequests() {
        repository.save(new RequestLog(request.getMethod(), request.getRequestURI(), Instant.now()));
    }

    @After("taskControllerMethods() && @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void afterPutRequests() {
        repository.save(new RequestLog(request.getMethod(), request.getRequestURI(), Instant.now()));
    }

    @After("taskControllerMethods() && @annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void afterPatchRequests() {
        repository.save(new RequestLog(request.getMethod(), request.getRequestURI(), Instant.now()));
    }

    @After("taskControllerMethods() && @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void afterDeleteRequests() {
        repository.save(new RequestLog(request.getMethod(), request.getRequestURI(), Instant.now()));
    }
}
