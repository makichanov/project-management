package com.makichanov.projectmanagement.service.impl;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makichanov.projectmanagement.exception.ResourceNotUpdatedException;
import com.makichanov.projectmanagement.model.dto.CreatingProjectDto;
import com.makichanov.projectmanagement.model.dto.ProjectDto;
import com.makichanov.projectmanagement.model.entity.Project;
import com.makichanov.projectmanagement.repository.ProjectRepository;
import com.makichanov.projectmanagement.repository.specifier.ProjectQueryParameters;
import com.makichanov.projectmanagement.repository.specifier.QueryBuilder;
import com.makichanov.projectmanagement.repository.specifier.QuerySpecifierChain;
import com.makichanov.projectmanagement.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ConversionService conversionService;
    private final QueryBuilder queryBuilder;

    @Override
    public List<ProjectDto> findByCriteria(ProjectCriteriaDto dto) {
        ProjectQueryParameters projectQueryParameters = new ProjectQueryParameters();
        if (dto != null) {
            QuerySpecifierChain querySpecifierChain = queryBuilder.getChain();
            querySpecifierChain.specifyQuery(dto, projectQueryParameters);
        }
        Specification<Project> specification = projectQueryParameters.getSpecification();
        Specification<Project> s = Specification.where(null);

        s.and(specification);
        Page<Project> projects = projectRepository.findAll(s,
                projectQueryParameters.getPageRequest());
        return ProjectDto.toProjectDtoList(projects.getContent());
    }

    @Override
    public Optional<ProjectDto> findById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.map(value -> conversionService.convert(value, ProjectDto.class));
    }

    @Override
    @Transactional
    public ProjectDto create(CreatingProjectDto dto) {
        Project toSave = conversionService.convert(dto, Project.class);
        Project project = projectRepository.save(toSave);
        return conversionService.convert(project, ProjectDto.class);
    }

    @Override
    @Transactional
    public Optional<ProjectDto> closeProject(Long projectId) {
        int updatedRows = projectRepository.setClosedStatus(projectId, true);
        if (updatedRows == 0) {
            throw new ResourceNotUpdatedException("Cannot update close status of project with id " + projectId);
        }
        Optional<Project> project = projectRepository.findById(projectId);
        return project.map(value -> conversionService.convert(value, ProjectDto.class));
    }

    @Override
    @Transactional
    public Optional<ProjectDto> deleteProject(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        projectRepository.deleteById(projectId);
        return project.map(value -> conversionService.convert(value, ProjectDto.class));
    }

}
