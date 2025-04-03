package com.toan.task_management_springboot.service;

import com.toan.task_management_springboot.model.Priority;
import com.toan.task_management_springboot.model.Status;
import com.toan.task_management_springboot.model.Task;
import com.toan.task_management_springboot.dto.CreateTaskDTO;
import com.toan.task_management_springboot.dto.UpdateTaskDTO;

import java.util.List;

/**
 * Service interface for managing tasks.
 */
public interface TaskService {

    /**
     * Retrieves all tasks.
     *
     * @return a list of all tasks
     */
    List<Task> getAllTasks();

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task
     * @return the task with the specified ID
     */
    Task getTaskById(Integer id);

    /**
     * Creates a new task.
     *
     * @param createTaskDTO the data transfer object containing task details
     * @return the created task
     */
    Task createTask(CreateTaskDTO createTaskDTO);

    /**
     * Updates an existing task.
     *
     * @param id            the ID of the task to update
     * @param updateTaskDTO the data transfer object updated task details
     * @return the updated task
     */
    Task updateTask(Integer id, UpdateTaskDTO udateTaskDTO);

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete
     */
    void deleteTask(Integer id);

    /**
     * Retrieves tasks by their status.
     *
     * @param status the status of the tasks to retrieve
     * @return a list of tasks with the specified status
     */
    List<Task> getTasksByStatus(Status status);

    /**
     * Retrieves tasks by their priority.
     *
     * @param priority the priority of the tasks to retrieve
     * @return a list of tasks with the specified priority
     */
    List<Task> getTasksByPriority(Priority priority);

    /**
     * Retrieves tasks by their status and priority.
     *
     * @param status   the status of the tasks to retrieve
     * @param priority the priority of the tasks to retrieve
     * @return a list of tasks with the specified status and priority
     */
    List<Task> getTasksByStatusAndPriority(Status status, Priority priority);

    /**
     * Searches for tasks by their title.
     *
     * @param title the title to search for
     * @return a list of tasks with titles matching the search term
     */
    List<Task> searchTasksByTitle(String title);
}
