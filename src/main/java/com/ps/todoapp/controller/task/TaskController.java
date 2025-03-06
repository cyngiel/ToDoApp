package com.ps.todoapp.controller.task;

import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.entity.task.SortBy;
import com.ps.todoapp.entity.task.Task;
import com.ps.todoapp.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public List<Task> getAll(@RequestParam(required = false) Priority priority, @RequestParam(required = false) SortBy sortBy, @RequestParam(required = false) boolean desc, @AuthenticationPrincipal UserDetails userDetails) {
        return taskService.getAll(userDetails, priority, sortBy, desc);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable("id") long id) {
        return taskService.findTaskById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody List<Task> tasks) {
        taskService.createTasks(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task taskDetails) {
        Task updated = taskService.updateTask(id, taskDetails);
        if (updated == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(taskDetails, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteRequestLog(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
