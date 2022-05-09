package com.makichanov.projectmanagement.controller;

import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.service.TaskService;
import com.makichanov.projectmanagement.model.dto.CreatingTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity readById(@PathVariable Long id) {
        Optional<TaskDto> task = taskService.findById(id);
        return task.isPresent()
                ? new ResponseEntity<>(task.get(), HttpStatus.OK)
                : new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto create(@RequestBody CreatingTaskDto dto) {
        return taskService.create(dto);
    }

    @PutMapping("/{taskId}/close")
    public ResponseEntity closeTask(@PathVariable Long taskId) {
        Optional<TaskDto> task = taskService.completeTask(taskId);
        return task.isPresent()
                ? new ResponseEntity<>(task.get(), HttpStatus.OK)
                : new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<TaskDto> task = taskService.deleteTask(id);
        return task.isPresent()
                ? new ResponseEntity<>(task.get(), HttpStatus.OK)
                : new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
    }

}
