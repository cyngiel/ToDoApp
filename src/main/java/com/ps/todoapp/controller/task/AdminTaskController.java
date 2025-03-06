package com.ps.todoapp.controller.task;

import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.entity.task.Task;
import com.ps.todoapp.service.task.AdminTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/admin/tasks")
public class AdminTaskController {

    @Autowired
    private AdminTaskService adminTaskService;


    @GetMapping()
    public List<Task> getAll(@RequestParam(required = false) Priority priority) {
        return adminTaskService.getTasks(priority);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable("id") long id) {
        return adminTaskService.findTaskById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody List<Task> tasks) {
        adminTaskService.saveAll(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task taskDetails) {
        return adminTaskService.updateTask(id, taskDetails);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRequestLog(@PathVariable Long id) {
        adminTaskService.deleteById(id);
    }

    @GetMapping("/count")
    public long getCount() {
        return adminTaskService.count();
    }

    @GetMapping("/count/{priorityString}")
    public long getCountByPriority(@PathVariable("priorityString") String priorityString) {
        return adminTaskService.getCountByPriority(priorityString);
    }
}
