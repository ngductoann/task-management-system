package com.toan.task_management_springboot.service;

import com.toan.task_management_springboot.model.Priority;
import com.toan.task_management_springboot.model.Status;
import com.toan.task_management_springboot.model.Task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Integer id);

    Task createTask(Task task);

    Task updateTask(Integer id, Task task);

    void deleteTask(Integer id);

    List<Task> getTasksByStatus(Status status);

    List<Task> getTasksByPriority(Priority priority);

    List<Task> getTasksByStatusAndPriority(Status status, Priority priority);

    List<Task> searchTasksByTitle(String title);
}
