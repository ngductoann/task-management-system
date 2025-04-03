package com.toan.task_management_springboot.task;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.notNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toan.task_management_springboot.controller.TaskController;
import com.toan.task_management_springboot.dto.CreateTaskDTO;
import com.toan.task_management_springboot.dto.UpdateTaskDTO;
import com.toan.task_management_springboot.model.Task;
import com.toan.task_management_springboot.service.TaskService;

@WebMvcTest(TaskController.class)
public class TaskControllerTests {
    private static final String BASE_URL = "/api/v1/tasks";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockitoBean
    TaskService taskService;

    @Test
    public void testGetAllTasksShouldReturn200Ok() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testGetTaskByIdShouldReturn200Ok() throws Exception {
        int taskId = 1;

        CreateTaskDTO task = new CreateTaskDTO();

        task.setTitle("Test Task");
        task.setDescription("This is a test task.");
        task.setPriority("HIGH");
        task.setDueDate("2023-10-01 10:00:00");

        Task taskEntity = task.toEntity();

        Mockito.when(taskService.getTaskById(taskId)).thenReturn(taskEntity);

        mockMvc.perform(get(BASE_URL + "/" + taskId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("This is a test task."))
                .andExpect(jsonPath("$.priority").value("HIGH"))
                .andExpect(jsonPath("$.due_date").value("2023-10-01T10:00:00"))
                .andExpect(jsonPath("$.status").value("OPEN"))
                .andDo(print());
    }

    @Test
    public void testAddShouldReturn201Created() throws Exception {
        CreateTaskDTO createTaskDTO = new CreateTaskDTO();

        createTaskDTO.setTitle("Test Task");
        createTaskDTO.setDescription("This is a test task.");
        createTaskDTO.setPriority("HIGH");
        createTaskDTO.setDueDate("2023-10-01 10:00:00");

        Task task = createTaskDTO.toEntity();

        Mockito.when(taskService.createTask(createTaskDTO)).thenReturn(task);

        String bodyContent = mapper.writeValueAsString(createTaskDTO);

        mockMvc.perform(post(BASE_URL).contentType("application/json").content(bodyContent))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("This is a test task."))
                .andExpect(jsonPath("$.priority").value("HIGH"))
                .andExpect(jsonPath("$.due_date").value("2023-10-01T10:00:00"))
                .andExpect(jsonPath("$.status").value("OPEN"))
                .andDo(print());

    }

    @Test
    public void testUpdateShouldReturn200Ok() throws Exception {
        UpdateTaskDTO updateTaskDTO = new UpdateTaskDTO();

        updateTaskDTO.setTitle("Updated Task");
        updateTaskDTO.setDescription("This is an updated task.");
        updateTaskDTO.setPriority("LOW");
        updateTaskDTO.setDueDate("2023-10-02 10:00:00");

        Task task = updateTaskDTO.toEntity();
        task.setId(1);

        Mockito.when(taskService.updateTask(1, updateTaskDTO)).thenReturn(task);

        String bodyContent = mapper.writeValueAsString(updateTaskDTO);

        mockMvc.perform(put(BASE_URL + "/1").contentType("application/json").content(bodyContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("This is an updated task."))
                .andExpect(jsonPath("$.priority").value("LOW"))
                .andExpect(jsonPath("$.due_date").value("2023-10-02T10:00:00"))
                .andExpect(jsonPath("$.status").value("OPEN"))
                .andDo(print());

    }

    @Test
    public void testDeleteShouldReturn204NoContent() throws Exception {
        int taskId = 1;

        Mockito.doNothing().when(taskService).deleteTask(taskId);

        mockMvc.perform(delete(BASE_URL + "/" + taskId))
                .andExpect(status().isNoContent());
    }

}
