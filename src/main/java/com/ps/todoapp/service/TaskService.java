package com.ps.todoapp.service;

import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.entity.task.SortBy;
import com.ps.todoapp.entity.task.Task;
import com.ps.todoapp.entity.user.User;
import com.ps.todoapp.repository.TaskRepository;
import com.ps.todoapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    TaskRepository taskRepository;
    UserRepository userRepository;

    public List<Task> getAll(UserDetails userDetails, Priority priority, SortBy sortBy, boolean desc) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        List<Task> tasks;
        if (priority != null) {
            tasks = taskRepository.findAllByUserIdAndPriority(user.getId(), priority);
        } else {
            tasks = taskRepository.findAllByUserId(user.getId());
        }

        if (sortBy != null) {
            sort(sortBy, desc, tasks);
        }

        return tasks;
    }

    public void createTasks(List<Task> tasks) {
        User user = getUserFromContext();
        tasks.forEach(task -> task.setUser(user));
        tasks.forEach(taskRepository::save);
    }

    public Task updateTask(long id, Task taskDetails) {
        User user = getUserFromContext();

        Optional<Task> task = taskRepository.findTaskByIdAndUserId(id, user.getId());
        if (task.isPresent()) {
            task.get().update(taskDetails);
            taskRepository.save(task.get());
        }

        return task.orElse(null);
    }

    public Task findTaskById(long id) {
        return taskRepository.findTaskByIdAndUserId(id, getUserFromContext().getId()).orElse(null);
    }

    public void deleteById(Long id) {
        taskRepository.delete(findTaskById(id));
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

    User getUserFromContext() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow();
    }

}
