package com.makichanov.projectmanagement.repository.specifier.impl;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makichanov.projectmanagement.model.entity.Project;
import com.makichanov.projectmanagement.repository.specification.ProjectsByDescriptionPartSpecification;
import com.makichanov.projectmanagement.repository.specifier.ProjectQueryParameters;
import com.makichanov.projectmanagement.repository.specifier.QuerySpecifier;
import com.makichanov.projectmanagement.repository.specifier.QuerySpecifierChain;
import org.springframework.data.jpa.domain.Specification;

public class ProjectDescriptionQuerySpecifierChain implements QuerySpecifier {
    @Override
    public void specifyQuery(ProjectCriteriaDto dto, ProjectQueryParameters projectQueryParameters,
                             QuerySpecifierChain nextSpecifier) {
        if (dto.getDescription() != null) {
            Specification<Project> projectsByDescriptionPartSpecification = new ProjectsByDescriptionPartSpecification(dto.getDescription());
            projectQueryParameters.setSpecification(projectsByDescriptionPartSpecification);
        }
        nextSpecifier.specifyQuery(dto, projectQueryParameters);
    }
}
