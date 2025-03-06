package com.ps.todoapp.controller.task;

import com.ps.todoapp.service.TaskCountService;
import com.ps.todoapp.service.TaskService;
import com.ps.todoapp.utils.EnumUtils;
import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/tasks/count")
public class TaskCountController {

    @Autowired
    private TaskRepository repository;
    @Autowired
    private TaskCountService taskCountService;

    @GetMapping()
    public long getCount() {
        return taskCountService.count();
    }

    @GetMapping("/{priorityString}")
    public long getCountByPriority(@PathVariable("priorityString") String priorityString) {
        Priority priority = EnumUtils.getEnumFromString(Priority.class, priorityString)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Key not found: " + priorityString));

        return taskCountService.countByPriority(priority);
    }

}
