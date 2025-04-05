package com.toan.task_management_springboot.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.hibernate.internal.build.AllowNonPortable;

import com.toan.task_management_springboot.model.Task;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toan.task_management_springboot.annotaion.EnumConstraint;
import com.toan.task_management_springboot.model.Priority;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for creating a Task.
 * This class is used to encapsulate the data required to create a Task entity.
 */
@NoArgsConstructor
@AllowNonPortable
@Data
public class CreateTaskDTO {

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
	 * The priority of the task (e.g., HIGH, MEDIUM, LOW).
	 */
	@NotBlank(message = "Priority is required")
	@EnumConstraint(enumClass = Priority.class, message = "Invalid priority value")
	private String priority;

	/**
	 * The due date of the task in the format "yyyy-MM-dd HH:mm:ss".
	 */
	@JsonProperty("due_date")
	private String dueDate;

	/**
	 * Converts this DTO to a Task entity.
	 *
	 * @return a Task entity populated with the data from this DTO.
	 */
	public Task toEntity() {
		Task task = new Task();

		task.setTitle(this.title);
		task.setDescription(this.description);

		// Set string to enum conversion for priority
		task.setPriority(Priority.valueOf(this.priority.toUpperCase()));

		// Convert dueDate string to LocalDateTime with error handling
		try {
			task.setDueDate(LocalDateTime.parse(this.dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid date format for dueDate. Expected format: yyyy-MM-dd HH:mm:ss",
					e);
		}

		return task;
	}
}
