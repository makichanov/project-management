package com.makochanov.projectmanagement.controller;

import com.makochanov.projectmanagement.model.dto.CreatingTaskDto;
import com.makochanov.projectmanagement.model.dto.TaskDto;
import com.makochanov.projectmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{projectId}")
    public List<TaskDto> readByProjectId(@PathVariable Long projectId) {
        return taskService.findByProjectId(projectId);
    }

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
        return taskService.closeTask(taskId);
    }

    @DeleteMapping("/{id}")
    public TaskDto delete(@PathVariable Long id) {
        return taskService.deleteTask(id);
    }

}
