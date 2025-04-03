package com.toan.task_management_springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a task entity in the task management system.
 * This entity is mapped to the "tasks" table in the database.
 */
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    /**
     * The unique identifier for the task.
     * This value is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The title of the task.
     */
    private String title;

    /**
     * A detailed description of the task.
     */
    private String description;

    /**
     * The current status of the task.
     * Defaults to {@code Status.OPEN}.
     */
    private Status status = Status.OPEN;

    /**
     * The priority level of the task.
     */
    private Priority priority;

    /**
     * The due date and time for the task.
     * Defaults to the current timestamp.
     */
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonProperty("due_date")
    private LocalDateTime dueDate;

    /**
     * The timestamp when the task was created.
     * This value is set automatically and cannot be updated.
     */
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    /**
     * The timestamp when the task was last updated.
     * This value is set automatically.
     */
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    /**
     * Sets the creation and update timestamps before persisting the entity.
     */
    @PrePersist
    private void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    /**
     * Updates the timestamp before updating the entity.
     */
    @PreUpdate
    private void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
