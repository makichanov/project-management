package com.makochanov.projectmanagement.service.impl;

import com.makochanov.projectmanagement.exception.ResourceNotFoundException;
import com.makochanov.projectmanagement.exception.ResourceNotUpdatedException;
import com.makochanov.projectmanagement.model.dto.CreatingProjectDto;
import com.makochanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makochanov.projectmanagement.model.dto.ProjectDto;
import com.makochanov.projectmanagement.model.entity.Project;
import com.makochanov.projectmanagement.repository.ProjectRepository;
import com.makochanov.projectmanagement.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ConversionService conversionService;

    @Override
    public List<ProjectDto> findByCriteria(ProjectCriteriaDto dto) {
        return null;
    }

    @Override
    public ProjectDto findById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        Project projectItem = project.orElseThrow(() -> new ResourceNotFoundException("Cannot find project resource with id " + id));
        return conversionService.convert(projectItem, ProjectDto.class);
    }

    @Override
    public ProjectDto create(CreatingProjectDto dto) {
        Project toSave = conversionService.convert(dto,Project.class);
        Project project = projectRepository.save(toSave);
        return conversionService.convert(project, ProjectDto.class);
    }

    @Override
    public ProjectDto closeProject(Long projectId) {
        int updatedRows = projectRepository.setClosedStatus(projectId, true);
        if (updatedRows == 0) {
            throw new ResourceNotUpdatedException("Cannot update close status of project with id " + projectId);
        }
        return findById(projectId);
    }

    @Override
    public ProjectDto deleteProject(Long projectId) {
        int updatedRows = projectRepository.setDeletedStatus(projectId, true);
        if (updatedRows == 0) {
            throw new ResourceNotUpdatedException("Cannot update delete status of project with id " + projectId);
        }
        return findById(projectId);
    }

}
