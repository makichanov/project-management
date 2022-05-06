package com.makichanov.projectmanagement.controller;

import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.service.TaskService;
import com.makichanov.projectmanagement.model.dto.CreatingTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public TaskDto readById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping
    public TaskDto create(@RequestBody CreatingTaskDto dto) {
        return taskService.create(dto);
    }

    @PutMapping("/{taskId}/close")
    public TaskDto closeTask(@PathVariable Long taskId) {
        return taskService.completeTask(taskId);
    }

    @DeleteMapping("/{id}")
    public TaskDto delete(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

}
