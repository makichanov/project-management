package com.makichanov.projectmanagement.service;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makichanov.projectmanagement.model.dto.ProjectDto;
import com.makichanov.projectmanagement.model.dto.CreatingProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> findByCriteria(ProjectCriteriaDto dto);

    ProjectDto findById(Long id);

    ProjectDto create(CreatingProjectDto dto);

    ProjectDto closeProject(Long projectId);

    ProjectDto deleteProject(Long projectId);

}
