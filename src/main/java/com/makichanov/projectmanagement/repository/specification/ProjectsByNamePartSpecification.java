package com.makichanov.projectmanagement.repository.specification;

import com.makichanov.projectmanagement.model.entity.Project;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class ProjectsByNamePartSpecification implements Specification<Project> {

    private String namePart;

    @Override
    public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        String formattedNamePart = String.format("%%%s%%", namePart);
        return criteriaBuilder.like(root.get("name"), formattedNamePart);
    }

}
