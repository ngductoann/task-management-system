package com.toan.task_management_springboot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.toan.task_management_springboot.model.Priority;
import com.toan.task_management_springboot.model.Status;
import com.toan.task_management_springboot.model.Task;
import com.toan.task_management_springboot.repository.TaskRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class TaskRepositoryTests {
    @Autowired
    private TaskRepository taskRepository;

    private Task insertTaskToDB(String title, String description, Priority priority,
            Status status, LocalDateTime dueDate) {

        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setStatus(status);
        task.setDueDate(dueDate);

        return taskRepository.save(task);
    }

    @Test
    public void testGetAllTaskSuccess() {
        insertTaskToDB("Test Task", "This is a test task.", Priority.HIGH, Status.OPEN,
                LocalDateTime.now().plusHours(8));
        insertTaskToDB("Test Task 2", "This is a test task 2.", Priority.MEDIUM, Status.IN_PROGRESS,
                LocalDateTime.now().plusHours(4));

        List<Task> tasks = taskRepository.findAll();

        assertNotNull(tasks);
        assertEquals(2, tasks.size());
    }

    @Test
    public void testFindTaskByIdFound() {
        Task taskSaved = insertTaskToDB("Test Task", "This is a test task.", Priority.HIGH, Status.OPEN,
                LocalDateTime.now().plusHours(8));

        Integer id = taskSaved.getId();

        Task task = taskRepository.findById(id).orElse(null);

        assertEquals(task.getId(), id);
        assertEquals("Test Task", task.getTitle());
        assertEquals(task.getDescription(), "This is a test task.");
        assertEquals(task.getPriority(), Priority.HIGH);
        assertEquals(task.getStatus(), Status.OPEN);
    }

    @Test
    public void testFindTaskByIdNotFound() {
        Integer id = 9999;

        Task task = taskRepository.findById(id).orElse(null);

        assertNull(task);
    }

    @Test
    public void testCreateSuccess() {
        Task task = new Task();

        task.setTitle("Test Task");
        task.setDescription("This is a test task.");
        task.setPriority(Priority.HIGH);
        task.setDueDate(LocalDateTime.now().plusHours(8));

        Task savedTask = taskRepository.save(task);
        System.out.println("Task saved with ID: " + savedTask.getId());
    }

    @Test
    public void testUpdateSuccess() {
        Task taskSaved = insertTaskToDB("Test Task", "This is a test task.", Priority.HIGH, Status.OPEN,
                LocalDateTime.now().plusHours(8));

        Integer id = taskSaved.getId();

        Task task = taskRepository.findById(id).orElse(null);

        task.setTitle("Updated Task");
        task.setDescription("This is an updated test task.");
        task.setPriority(Priority.MEDIUM);
        task.setStatus(Status.IN_PROGRESS);
        task.setDueDate(LocalDateTime.now().plusHours(4));

        Task updatedTask = taskRepository.save(task);

        assertEquals(updatedTask.getId(), id);
        assertEquals("Updated Task", updatedTask.getTitle());
        assertEquals(updatedTask.getDescription(), "This is an updated test task.");
        assertEquals(updatedTask.getPriority(), Priority.MEDIUM);
        assertEquals(updatedTask.getStatus(), Status.IN_PROGRESS);
    }

    @Test
    public void testDeleteSuccess() {
        Task taskSaved = insertTaskToDB("Test Task", "This is a test task.", Priority.HIGH, Status.OPEN,
                LocalDateTime.now().plusHours(8));

        Integer id = taskSaved.getId();

        taskRepository.deleteById(id);

        Task task = taskRepository.findById(id).orElse(null);

        assertEquals(task, null);
    }
}
