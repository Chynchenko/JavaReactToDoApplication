package com.chynchenko.todo.controller;

import com.chynchenko.todo.exception.ResourceNotFoundException;
import com.chynchenko.todo.model.Task;
import com.chynchenko.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @GetMapping
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable  long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id:" + id));
        return ResponseEntity.ok(task);
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable long id,@RequestBody Task taskDetails) {
        Task updateTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id: " + id));

        updateTask.setName(taskDetails.getName());
        updateTask.setDescription(taskDetails.getDescription());
        updateTask.setComment(taskDetails.getComment());

        taskRepository.save(updateTask);

        return ResponseEntity.ok(updateTask);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable long id){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id: " + id));

        taskRepository.delete(task);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
