package com.ps.todoapp.service.appinfo;

import com.ps.todoapp.entity.appinfo.AppInfoDTO;
import com.ps.todoapp.repository.RequestLogRepository;
import com.ps.todoapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.ps.todoapp.entity.task.Priority.HIGH;
import static com.ps.todoapp.entity.task.Priority.LOW;
import static com.ps.todoapp.entity.task.Priority.MEDIUM;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Service
@AllArgsConstructor
public class AppInfoService {

    private TaskRepository taskRepository;
    private RequestLogRepository requestLogRepository;

    public AppInfoDTO getInfo(){
        return AppInfoDTO.builder()
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
