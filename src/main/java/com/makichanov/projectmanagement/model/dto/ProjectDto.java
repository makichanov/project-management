package com.makichanov.projectmanagement.model.dto;

import com.makichanov.projectmanagement.converter.ProjectToProjectDtoConverter;
import com.makichanov.projectmanagement.model.entity.Project;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectDto {

    Long id;

    String name;

    String description;

    Timestamp createDate;

    Boolean isDeleted;

    Boolean isClosed;

    List<TaskDto> tasks;

    public static List<ProjectDto> toProjectDtoList(Collection<Project> projects) {
        ProjectToProjectDtoConverter converter = new ProjectToProjectDtoConverter();
        return projects.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

}
