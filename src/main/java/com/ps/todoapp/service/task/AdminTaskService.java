package com.ps.todoapp.service.task;

import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.entity.task.Task;
import com.ps.todoapp.repository.TaskRepository;
import com.ps.todoapp.utils.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdminTaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getTasks(Priority priority) {
        if (priority == null) {
            return repository.findAll();
        }
        return repository.findAllByPriority(priority);
    }

    public Task findTaskById(long id) {
        return repository.findTaskById(id);
    }

    public void saveAll(List<Task> tasks) {
        repository.saveAll(tasks);
    }

    public ResponseEntity<Task> updateTask(Long id, Task taskDetails) {
        Optional<Task> task = Optional.ofNullable(repository.findTaskById(id));
        if (task.isPresent()) {
            task.get().update(taskDetails);
            repository.save(task.get());
            return new ResponseEntity<>(taskDetails, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }

    public long getCountByPriority(String priorityString) {
        Priority priority = EnumUtils.getEnumFromString(Priority.class, priorityString)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Key not found: " + priorityString));

        return repository.countByPriority(priority);
    }
}
