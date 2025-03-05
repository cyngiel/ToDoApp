package com.ps.todoapp.task;

import com.ps.todoapp.EnumUtils;
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

    @GetMapping()
    public long getCount() {
        return repository.count();
    }

    @GetMapping("/{priorityString}")
    public long getCountByPriority(@PathVariable("priorityString") String priorityString) {
        Priority priority = EnumUtils.getEnumFromString(Priority.class, priorityString)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Key not found: " + priorityString));
        return repository.countByPriority(priority);
    }

}
