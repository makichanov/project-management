package com.makichanov.projectmanagement.repository.specifier;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;

public interface QuerySpecifier {

    void specifyQuery(ProjectCriteriaDto dto, ProjectQueryParameters projectQueryParameters,
                      QuerySpecifierChain nextSpecifier);

}
