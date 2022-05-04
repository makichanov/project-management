package com.makochanov.projectmanagement.service;

import com.makochanov.projectmanagement.model.dto.CreatingProjectDto;
import com.makochanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makochanov.projectmanagement.model.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findByCriteria(ProjectCriteriaDto dto);

    ProjectDto findById(Long id);

    ProjectDto create(CreatingProjectDto dto);

    ProjectDto closeProject(Long projectId);

    ProjectDto deleteProject(Long projectId);

}
