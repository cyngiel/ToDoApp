package com.ps.todoapp.repository;

import com.ps.todoapp.entity.task.Priority;
import com.ps.todoapp.entity.task.Task;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for performing CRUD operations on {@link Task} entities.
 * <p>
 * This interface extends CrudRepository, providing standard methods for
 * creating, reading, updating, and deleting Task entities in the database.
 * </p>
 *
 * @see ListCrudRepository
 * @see Task
 */
public interface TaskRepository extends ListCrudRepository<Task, Long> {
    Task findTaskById(long id);
    List<Task> findTaskByTitle(String title);
    List<Task> findAllByPriority(Priority priority);

    Optional<Task> findTaskByIdAndUserId(long id, long userId);
    List<Task> findAllByUserId(long userId);
    List<Task> findAllByUserIdAndPriority(long userId, Priority priority);

    long countByPriority(Priority priority);
    long countByUserId(long userId);
    long countByUserIdAndPriority(long userId, Priority priority);
}
