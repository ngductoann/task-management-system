package com.toan.task_management_springboot.service.impl;

import com.toan.task_management_springboot.model.Priority;
import com.toan.task_management_springboot.model.Status;
import com.toan.task_management_springboot.model.Task;
import com.toan.task_management_springboot.service.TaskService;
import com.toan.task_management_springboot.repository.TaskRepository;
import com.toan.task_management_springboot.dto.CreateTaskDTO;
import com.toan.task_management_springboot.dto.UpdateTaskDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    public TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " does not exist."));
    }

    @Override
    public Task createTask(CreateTaskDTO createTaskDTO) {
        return taskRepository.save(createTaskDTO.toEntity());
    }

    @Override
    public Task updateTask(Integer id, UpdateTaskDTO updateTaskDTO) {
        if (taskRepository.existsById(id)) {
            Task existingTask = taskRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " does not exist."));

            Task updatedTask = updateTaskDTO.toEntity();
            updatedTask.setId(id);
            updatedTask.setCreatedAt(existingTask.getCreatedAt()); // Preserve createdAt

            return taskRepository.save(updatedTask);
        } else {
            throw new IllegalArgumentException("Task with ID " + id + " does not exist.");
        }
    }

    @Override
    public void deleteTask(Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Task with ID " + id + " does not exist.");
        }
    }

    @Override
    public List<Task> getTasksByStatus(Status status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTasksByStatus'");
    }

    @Override
    public List<Task> getTasksByPriority(Priority priority) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTasksByPriority'");
    }

    @Override
    public List<Task> getTasksByStatusAndPriority(Status status, Priority priority) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Unimplemented method 'getTasksByStatusAndPriority'");
    }

    @Override
    public List<Task> searchTasksByTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchTasksByTitle'");
    }
}
