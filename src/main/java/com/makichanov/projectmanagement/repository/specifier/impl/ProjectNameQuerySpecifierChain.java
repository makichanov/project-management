package com.makichanov.projectmanagement.repository.specifier.impl;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;

import com.makichanov.projectmanagement.model.entity.Project;
import com.makichanov.projectmanagement.repository.specification.ProjectsByNamePartSpecification;
import com.makichanov.projectmanagement.repository.specifier.ProjectQueryParameters;
import com.makichanov.projectmanagement.repository.specifier.QuerySpecifier;
import com.makichanov.projectmanagement.repository.specifier.QuerySpecifierChain;
import org.springframework.data.jpa.domain.Specification;

public class ProjectNameQuerySpecifierChain implements QuerySpecifier {

    @Override
    public void specifyQuery(ProjectCriteriaDto dto,
                             ProjectQueryParameters projectQueryParameters, QuerySpecifierChain nextSpecifier) {
        if (dto.getName() != null) {
            Specification<Project> projectsByNamePartSpecification = new ProjectsByNamePartSpecification(dto.getName());
            projectQueryParameters.setSpecification(projectsByNamePartSpecification);
        }
        nextSpecifier.specifyQuery(dto, projectQueryParameters);
    }

}
