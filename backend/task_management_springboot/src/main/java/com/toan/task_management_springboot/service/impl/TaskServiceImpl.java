package com.toan.task_management_springboot.service.impl;

import com.toan.task_management_springboot.model.Priority;
import com.toan.task_management_springboot.model.Status;
import com.toan.task_management_springboot.model.Task;
import com.toan.task_management_springboot.service.TaskService;
import com.toan.task_management_springboot.repository.TaskRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class TaskServiceImpl implements TaskService {
    @Autowired
    public TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTasks'");
    }

    @Override
    public Task getTaskById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTaskById'");
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Integer id, Task task) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTask'");
    }

    @Override
    public void deleteTask(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTask'");
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
