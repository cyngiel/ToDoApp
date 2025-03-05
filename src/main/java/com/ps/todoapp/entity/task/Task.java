package com.ps.todoapp.entity.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;


/**
 * Represents a single task entity with a title, description, and priority.
 * <p>
 * The primary key is type of Long
 * </p>
 *
 * @see Priority
 */
@Entity
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private Priority priority;
    private Instant created;
    private Instant lastModify;

    public Task(String title, String description, Priority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        created = Instant.now();
        lastModify = created;
    }

    /**
     * Updates the current task.
     * The method sets the title, description, priority, and the last modification timestamp
     * based on the information from the provided taskDetails.
     *
     * @param taskDetails The Task object containing the new task details to update. Must not be null.
     */
    public void update(Task taskDetails) {
        setTitle(taskDetails.getTitle());
        setDescription(taskDetails.getDescription());
        setPriority(taskDetails.getPriority());
        setLastModify(Instant.now());
    }
}
