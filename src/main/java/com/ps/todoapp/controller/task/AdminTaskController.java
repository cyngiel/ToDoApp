package com.ps.todoapp.controller.task;

import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.entity.task.Task;
import com.ps.todoapp.repository.TaskRepository;
import com.ps.todoapp.utils.EnumUtils;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/tasks")
public class AdminTaskController {

    @Autowired
    private TaskRepository taskRepository;


    @GetMapping()
    public List<Task> getAll(@RequestParam(required = false) Priority priority) {
        if (priority == null) {
            return taskRepository.findAll();
        }
        return taskRepository.findAllByPriority(priority);
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable("id") long id) {
        return taskRepository.findTaskById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTask(@RequestBody List<Task> tasks) {
        tasks.forEach(taskRepository::save);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task taskDetails) {
        Optional<Task> task = Optional.ofNullable(taskRepository.findTaskById(id));
        if (task.isPresent()) {
            task.get().update(taskDetails);
            taskRepository.save(task.get());
            return new ResponseEntity<>(taskDetails, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public void deleteRequestLog(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

    @GetMapping("/count")
    public long getCount() {
        return taskRepository.count();
    }

    @GetMapping("/count/{priorityString}")
    public long getCountByPriority(@PathVariable("priorityString") String priorityString) {
        Priority priority = EnumUtils.getEnumFromString(Priority.class, priorityString)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Key not found: " + priorityString));

        return taskRepository.countByPriority(priority);
    }
}
