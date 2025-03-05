package com.ps.todoapp.appinfo;

import com.ps.todoapp.repository.RequestLogRepository;
import com.ps.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static com.ps.todoapp.entity.task.Priority.HIGH;
import static com.ps.todoapp.entity.task.Priority.LOW;
import static com.ps.todoapp.entity.task.Priority.MEDIUM;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@RestController
@RequestMapping("/info")
public class AppInfoController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RequestLogRepository requestLogRepository;

    @GetMapping()
    public AppInfo getInfo() {
        return AppInfo.builder()
                .timestamp(Instant.now())
                .totalTasks(taskRepository.count())
                .highPriorityTasks(taskRepository.countByPriority(HIGH))
                .mediumPriorityTasks(taskRepository.countByPriority(MEDIUM))
                .lowPriorityTasks(taskRepository.countByPriority(LOW))
                .totalTasksRequests(requestLogRepository.count())
                .getRequests(requestLogRepository.countByMethod(String.valueOf(GET)))
                .postRequests(requestLogRepository.countByMethod(String.valueOf(POST)))
                .putRequests(requestLogRepository.countByMethod(String.valueOf(PUT)))
                .patchRequest(requestLogRepository.countByMethod(String.valueOf(PATCH)))
                .deleteRequests(requestLogRepository.countByMethod(String.valueOf(DELETE)))
                .build();
    }
}
