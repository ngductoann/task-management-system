package com.toan.task_management_springboot.dto;

import com.toan.task_management_springboot.model.Task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

import com.toan.task_management_springboot.annotaion.EnumConstraint;
import com.toan.task_management_springboot.model.Priority;
import com.toan.task_management_springboot.model.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDTO {

    /**
     * The title of the task.
     */
    @NotBlank(message = "Title is required")
    private String title;

    /**
     * The description of the task.
     */
    @NotBlank(message = "Description is required")
    private String description;

    /**
     * The status of the task (e.g., OPEN, IN_PROGRESS, COMPLETED).
     */
    @NotBlank(message = "Status is required")
    @EnumConstraint(enumClass = Status.class, message = "Invalid status value")
    private String status;

    /**
     * The priority of the task (e.g., HIGH, MEDIUM, LOW).
     */
    @NotBlank(message = "Priority is required")
    @EnumConstraint(enumClass = Priority.class, message = "Invalid priority value")
    private String priority;

    /**
     * The due date of the task in the format "yyyy-MM-dd HH:mm:ss".
     */
    @NotNull(message = "Due date is required")
    private String dueDate;

    /**
     * Converts the UpdateTaskDTO object to a Task entity.
     *
     * @return a Task entity populated with the data from this DTO.
     */
    public Task toEntity() {
        Task task = new Task();

        // Set the title of the task
        task.setTitle(this.title);

        // Set the description of the task
        task.setDescription(this.description);

        // Convert and set the status of the task
        task.setStatus(Status.valueOf(this.status.toUpperCase()));

        // Convert and set the priority of the task
        task.setPriority(Priority.valueOf(this.priority.toUpperCase()));

        // Parse and set the due date of the task
        if (this.dueDate != "") {
            task.setDueDate(
                    java.time.LocalDateTime.parse(this.dueDate,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }

        return task;
    }
}
