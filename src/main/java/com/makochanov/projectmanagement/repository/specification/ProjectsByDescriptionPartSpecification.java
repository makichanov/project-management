package com.makochanov.projectmanagement.repository.specification;

import com.makochanov.projectmanagement.model.entity.Project;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProjectsByDescriptionPartSpecification implements Specification<Project> {

    private String descriptionPart;

    @Override
    public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String formattedDescriptionPart = String.format("%%%s%%", descriptionPart);
        return criteriaBuilder.like(root.get("p_name"), formattedDescriptionPart);
    }

}
