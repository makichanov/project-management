package com.makochanov.projectmanagement.converter;

import com.makochanov.projectmanagement.model.dto.ProjectDto;
import com.makochanov.projectmanagement.model.entity.Project;
import org.springframework.core.convert.converter.Converter;

public class ProjectToProjectDtoConverter implements Converter<Project, ProjectDto> {

    @Override
    public ProjectDto convert(Project source) {
       ProjectDto projectDto = new ProjectDto();
       projectDto.setId(source.getId());
       projectDto.setName(source.getName());
       projectDto.setDescription(source.getDescription());
       projectDto.setCreateDate(source.getCreateDate());
       projectDto.setIsDeleted(source.getIsDeleted());
       projectDto.setTasks(source.getTasks());
       return projectDto;
    }

}
