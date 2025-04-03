package com.toan.task_management_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.toan.task_management_springboot.service.TaskService;
import com.toan.task_management_springboot.model.Task;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        try {
            Task taskSaved = taskService.createTask(task);
            return ResponseEntity.ok(taskSaved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating task: " + e.getMessage());
        }

    }
}
