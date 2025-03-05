package com.ps.todoapp.task;

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
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping()
    public List<Task> getAll(@RequestParam(required = false) Priority priority) {
        if(priority == null) {
            return repository.findAll();
        }

        return repository.findAllByPriority(priority);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable("id") long id) {
        return repository.findTaskById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody List<Task> tasks) {
        tasks.forEach(repository::save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task taskDetails) {
        Task task = repository.findById(id).orElse(null);
        if (task == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        task.update(taskDetails);
        repository.save(task);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteRequestLog(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
