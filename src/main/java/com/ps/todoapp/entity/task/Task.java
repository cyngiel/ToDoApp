package com.ps.todoapp.entity.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ps.todoapp.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    private Instant created;
    @UpdateTimestamp
    private Instant lastModify;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String title;
    private String description;
    private Priority priority;

    public Task(String title, String description, Priority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    /**
     * Updates the current task.
     * The method sets the title, description, priority based on the information from the provided taskDetails.
     *
     * @param taskDetails The Task object containing the new task details to update. Must not be null.
     */
    public void update(Task taskDetails) {
        setTitle(taskDetails.getTitle());
        setDescription(taskDetails.getDescription());
        setPriority(taskDetails.getPriority());
    }
}
