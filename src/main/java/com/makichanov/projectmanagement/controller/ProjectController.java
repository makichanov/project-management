package com.makichanov.projectmanagement.controller;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makichanov.projectmanagement.model.dto.CreatingProjectDto;
import com.makichanov.projectmanagement.model.dto.ProjectDto;
import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.service.ProjectService;
import com.makichanov.projectmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDto> read(@RequestBody(required = false) ProjectCriteriaDto projectCriteriaDto) {
        return projectService.findByCriteria(projectCriteriaDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        Optional<ProjectDto> projectDto = projectService.findById(id);
        return projectDto.isPresent()
                ? new ResponseEntity<>(projectDto.get(), HttpStatus.OK)
                : new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{projectId}/tasks")
    public List<TaskDto> readByProjectId(@PathVariable Long projectId) {
        return taskService.findByProjectId(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto create(@RequestBody CreatingProjectDto dto) {
        return projectService.create(dto);
    }

    @PutMapping("/{id}/close")
    public ResponseEntity closeProject(@PathVariable Long id) {
        Optional<ProjectDto> projectDto = projectService.closeProject(id);
        return projectDto.isPresent()
                ? new ResponseEntity<>(projectDto.get(), HttpStatus.OK)
                : new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<ProjectDto> projectDto = projectService.deleteProject(id);
        return projectDto.isPresent()
                ? new ResponseEntity<>(projectDto.get(), HttpStatus.OK)
                : new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
    }

}
