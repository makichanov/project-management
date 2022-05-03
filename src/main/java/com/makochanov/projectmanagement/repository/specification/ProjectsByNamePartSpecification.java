package com.makochanov.projectmanagement.repository.specification;

import com.makochanov.projectmanagement.model.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class ProjectsByNamePartSpecification implements Specification<Project> {

    private String namePart;

    @Override
    public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String formattedNamePart = String.format("%%%s%%", namePart);
        return criteriaBuilder.like(root.get("p_name"), formattedNamePart);
    }

}
