package com.chynchenko.todo.repository;

import com.chynchenko.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository <Task, Long>{
}
