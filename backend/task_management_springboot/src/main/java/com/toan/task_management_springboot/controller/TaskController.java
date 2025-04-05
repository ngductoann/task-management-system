package com.toan.task_management_springboot.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.toan.task_management_springboot.service.TaskService;
import com.toan.task_management_springboot.model.Task;
import com.toan.task_management_springboot.dto.CreateTaskDTO;
import com.toan.task_management_springboot.dto.UpdateTaskDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping
	public ResponseEntity<?> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTasks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getTaskById(@PathVariable Integer id) {
		Task task = taskService.getTaskById(id);

		if (task == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(task);
	}

	@PostMapping()
	public ResponseEntity<?> createTask(@RequestBody @Validated CreateTaskDTO createTaskDTO) {
		Task taskSaved = taskService.createTask(createTaskDTO);
		URI location = URI.create("/api/v1/tasks/" + taskSaved.getId());

		return ResponseEntity.created(location).body(taskSaved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@PathVariable Integer id,
			@RequestBody UpdateTaskDTO updateTaskDTO) {
		Task taskUpdated = taskService.updateTask(id, updateTaskDTO);

		return ResponseEntity.ok(taskUpdated);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Integer id) {
		taskService.deleteTask(id);

		return ResponseEntity.noContent().build();
	}
}
