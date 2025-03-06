package com.ps.todoapp.service;

import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.repository.TaskRepository;
import com.ps.todoapp.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskCountService extends TaskService {

    public TaskCountService(TaskRepository taskRepository, UserRepository userRepository) {
        super(taskRepository, userRepository);
    }


    public long count() {
        return taskRepository.countByUserId(getUserFromContext().getId());
    }

    public long countByPriority(Priority priority) {
        return taskRepository.countByUserIdAndPriority(getUserFromContext().getId(), priority);
    }
}
