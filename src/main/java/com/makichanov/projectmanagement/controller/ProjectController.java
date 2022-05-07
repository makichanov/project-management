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
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDto> read(
            @RequestBody(required = false) ProjectCriteriaDto projectCriteriaDto,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "Page size should be greater than or equal to zero")
                    Long pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "50")
            @Min(value = 1, message = "Page size should be represented as positive number")
            @Max(value = 100, message = "Page size should not be greater than 100")
                    Long pageSize) {
        return projectService.findByCriteria(projectCriteriaDto, pageNum, pageSize);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto read(@PathVariable Long id) {
        return projectService.findById(id);
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
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto closeProject(@PathVariable Long id) {
        return projectService.closeProject(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto delete(@PathVariable Long id) {
        return projectService.deleteProject(id);
    }

}
