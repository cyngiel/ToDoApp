package com.ps.todoapp.task;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

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

    long countByPriority(Priority priority);
}
