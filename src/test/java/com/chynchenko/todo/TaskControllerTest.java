package com.chynchenko.todo;
import com.chynchenko.todo.controller.TaskController;
import com.chynchenko.todo.model.Task;
import com.chynchenko.todo.repository.TaskRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.mockito.*;
import org.mockito.junit.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskController taskController;

    @Test
    public void testGetAllTasks() {
        // Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", "Description 1", "Comment 1"));
        tasks.add(new Task(2, "Task 2", "Description 2", "Comment 2"));
        tasks.add(new Task(3, "Task 3", "Description 3", "Comment 3"));
        Mockito.when(taskRepository.findAll()).thenReturn(tasks);

        // When
        List<Task> result = taskController.getAllTasks();

        // Then
        assertEquals(3, result.size());
        assertEquals("Task 1", result.get(0).getName());
        assertEquals("Description 2", result.get(1).getDescription());
        assertEquals("Comment 3", result.get(2).getComment());
    }

    @Test
    public void testCreateTask() {
        // Given
        Task task = new Task(1, "Task 1", "Description 1", "Comment 1");
        Mockito.when(taskRepository.save(task)).thenReturn(task);

        // When
        Task result = taskController.createTask(task);

        // Then
        assertEquals("Task 1", result.getName());
        assertEquals("Description 1", result.getDescription());
        assertEquals("Comment 1", result.getComment());
    }

    @Test
    public void testGetTaskById() {
        // Given
        long id = 1;
        Task task = new Task(id, "Task 1", "Description 1", "Comment 1");
        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        // When
        ResponseEntity<Task> result = taskController.getTaskById(id);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Task 1", result.getBody().getName());
        assertEquals("Description 1", result.getBody().getDescription());
        assertEquals("Comment 1", result.getBody().getComment());
    }

    @Test
    public void testUpdateTask() {
        // Given
        long id = 1;
        Task existingTask = new Task(id, "Task 1", "Description 1", "Comment 1");
        Task updatedTask = new Task(id, "Task 1 updated", "Description 1 updated", "Comment 1 updated");
        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(existingTask));
        Mockito.when(taskRepository.save(existingTask)).thenReturn(updatedTask);

        // When
        ResponseEntity<Task> result = taskController.updateTask(id, updatedTask);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Task 1 updated", result.getBody().getName());
        assertEquals("Description 1 updated", result.getBody().getDescription());
        assertEquals("Comment 1 updated", result.getBody().getComment());
    }

    @Test
    public void testDeleteTask() {
        // Given
        long id = 1;
        Task task = new Task(id, "Task 1", "Description 1", "Comment 1");
        Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(task));

        // When
        ResponseEntity<HttpStatus> result = taskController.deleteTask(id);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
    }
}