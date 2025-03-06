package com.ps.todoapp.service;

import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.entity.task.SortBy;
import com.ps.todoapp.entity.task.Task;
import com.ps.todoapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository repository;

    public List<Task> getAll(Priority priority, SortBy sortBy, boolean desc) {
        List<Task> tasks;
        if (priority != null) {
            tasks = repository.findAllByPriority(priority);
        } else {
            tasks = repository.findAll();
        }

        if (sortBy != null) {
            sort(sortBy, desc, tasks);
        }

        return tasks;
    }

    public Task updateTask(long id, Task taskDetails) {
        Optional<Task> task = repository.findById(id);
        if (task.isPresent()) {
            task.get().update(taskDetails);
            repository.save(task.get());
        }

        return task.orElse(null);
    }

    private static void sort(SortBy sortBy, boolean desc, List<Task> tasks) {
        Comparator<Task> comparator;
        switch (sortBy) {
            case TITLE -> comparator = Comparator.comparing(Task::getTitle);
            case PRIORITY -> comparator = Comparator.comparing(Task::getPriority);
            case CREATED -> comparator = Comparator.comparing(Task::getCreated);
            default -> comparator = Comparator.comparing(Task::getId);
        }
        if (desc) {
            comparator = comparator.reversed();
        }
        tasks.sort(comparator);
    }
}
