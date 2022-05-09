package com.makichanov.projectmanagement.repository.specifier.impl;

import com.makichanov.projectmanagement.model.dto.ProjectCriteriaDto;
import com.makichanov.projectmanagement.repository.specifier.ProjectQueryParameters;
import com.makichanov.projectmanagement.repository.specifier.QuerySpecifier;
import com.makichanov.projectmanagement.repository.specifier.QuerySpecifierChain;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class ProjectsPagingQuerySpecifierChain implements QuerySpecifier {
    @Override
    public void specifyQuery(ProjectCriteriaDto dto, ProjectQueryParameters projectQueryParameters,
                             QuerySpecifierChain nextSpecifier) {
        Sort.Direction direction = dto.isOrderDesc()
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        PageRequest pageRequest = dto.getSortBy() != null
                ? PageRequest.of(dto.getPageNum().intValue(), dto.getPageSize().intValue(),
                    Sort.by(direction, dto.getSortBy()))
                : PageRequest.of(dto.getPageNum().intValue(), dto.getPageSize().intValue());
        projectQueryParameters.setPageRequest(pageRequest);
        nextSpecifier.specifyQuery(dto, projectQueryParameters);
    }
}
