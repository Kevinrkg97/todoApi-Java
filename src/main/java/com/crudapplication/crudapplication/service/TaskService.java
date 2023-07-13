package com.crudapplication.crudapplication.service;

import com.crudapplication.crudapplication.exceptions.ToDoExceptions;
import com.crudapplication.crudapplication.mapper.TaskInDTOToTask;
import com.crudapplication.crudapplication.persistence.entity.Task;
import com.crudapplication.crudapplication.persistence.entity.TaskStatus;
import com.crudapplication.crudapplication.persistence.repository.TaskRepository;
import com.crudapplication.crudapplication.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;
    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO) {
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("No se encontró la tarea", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }
    public void deleteById(Long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("No se encontró la tarea", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}
