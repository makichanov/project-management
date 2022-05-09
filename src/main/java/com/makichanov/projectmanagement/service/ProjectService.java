package com.makichanov.projectmanagement.service;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makichanov.projectmanagement.model.dto.ProjectDto;
import com.makichanov.projectmanagement.model.dto.CreatingProjectDto;

import java.util.List;
import java.util.Optional;

/**
 * The interface Project service.
 */
public interface ProjectService {

    /**
     * Find by criteria list.
     *
     * @param dto the dto
     * @return the list
     */
    List<ProjectDto> findByCriteria(ProjectCriteriaDto dto);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<ProjectDto> findById(Long id);

    /**
     * Create project dto.
     *
     * @param dto the dto
     * @return the project dto
     */
    ProjectDto create(CreatingProjectDto dto);

    /**
     * Close project optional.
     *
     * @param projectId the project id
     * @return the optional
     */
    Optional<ProjectDto> closeProject(Long projectId);

    /**
     * Delete project optional.
     *
     * @param projectId the project id
     * @return the optional
     */
    Optional<ProjectDto> deleteProject(Long projectId);

}
