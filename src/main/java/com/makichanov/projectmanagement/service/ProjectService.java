package com.makichanov.projectmanagement.service;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makichanov.projectmanagement.model.dto.ProjectDto;
import com.makichanov.projectmanagement.model.dto.CreatingProjectDto;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<ProjectDto> findByCriteria(ProjectCriteriaDto dto);

    Optional<ProjectDto> findById(Long id);

    ProjectDto create(CreatingProjectDto dto);

    Optional<ProjectDto> closeProject(Long projectId);

    Optional<ProjectDto> deleteProject(Long projectId);

}
