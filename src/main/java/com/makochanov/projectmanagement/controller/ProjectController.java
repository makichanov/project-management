package com.makochanov.projectmanagement.controller;

import com.makochanov.projectmanagement.model.dto.CreatingProjectDto;
import com.makochanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makochanov.projectmanagement.model.dto.ProjectDto;
import com.makochanov.projectmanagement.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProjectDto> read(@RequestBody(required = false) ProjectCriteriaDto projectCriteriaDto) {
        return projectService.findByCriteria(projectCriteriaDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDto read(@PathVariable Long id) {
        return projectService.findById(id);
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
