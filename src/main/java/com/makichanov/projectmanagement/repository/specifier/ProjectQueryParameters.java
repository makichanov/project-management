package com.makichanov.projectmanagement.repository.specifier;

import com.makichanov.projectmanagement.model.entity.Project;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectQueryParameters {

    List<Specification<Project>> specifications;

    PageRequest pageRequest;

    public ProjectQueryParameters() {
        this.pageRequest = PageRequest.ofSize(15);
        specifications= new ArrayList<>();
    }

    public Specification<Project> getSpecification() {
        Specification<Project> specification = Specification.where(null);
        for (Specification<Project> s : specifications) {
            specification.and(s);
        }
        return specification;
    }

    public void setSpecification(Specification<Project> specification) {
        specifications.add(specification);
    }

}
