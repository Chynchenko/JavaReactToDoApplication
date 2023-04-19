package com.chynchenko.todo;

import com.chynchenko.todo.model.Task;
import com.chynchenko.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
    @Autowired
    private TaskRepository taskRepository;

	@Override
	public void run(String... args) throws Exception {

	}
}
