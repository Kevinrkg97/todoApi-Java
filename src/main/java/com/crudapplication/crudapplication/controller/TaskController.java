package com.crudapplication.crudapplication.controller;

import com.crudapplication.crudapplication.persistence.entity.Task;
import com.crudapplication.crudapplication.persistence.entity.TaskStatus;
import com.crudapplication.crudapplication.service.TaskService;
import com.crudapplication.crudapplication.service.dto.TaskInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO){

        return this.taskService.createTask(taskInDTO);
    };

    @GetMapping
    public List<Task> findAll() {
        return this.taskService.findAll();
    };

    @GetMapping("/status/{status}")
    public List<Task> findAllByStatus(@PathVariable("status")TaskStatus status){
        return this.taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("/markAsFinished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id") Long id){
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    };
}
