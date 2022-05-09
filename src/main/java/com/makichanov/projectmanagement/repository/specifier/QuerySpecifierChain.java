package com.makichanov.projectmanagement.repository.specifier;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuerySpecifierChain {

    private final QuerySpecifier nextSpecifier;
    private QuerySpecifierChain nextSpecifierChain;

    public void specifyQuery(ProjectCriteriaDto dto, ProjectQueryParameters projectQueryParameters) {
        if (nextSpecifier != null) {
            nextSpecifier.specifyQuery(dto, projectQueryParameters, nextSpecifierChain);
        }
    }

    protected void setNextChain(QuerySpecifierChain chain) {
        nextSpecifierChain = chain;
    }

}
