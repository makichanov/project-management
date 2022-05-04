package com.makochanov.projectmanagement.converter;

import com.makochanov.projectmanagement.model.dto.CreatingProjectDto;
import com.makochanov.projectmanagement.model.entity.Project;
import org.springframework.core.convert.converter.Converter;

public class CreatingProjectDtoToProjectConverter implements Converter<CreatingProjectDto, Project> {

    @Override
    public Project convert(CreatingProjectDto source) {
        Project project = new Project();
        project.setName(source.getName());
        project.setDescription(source.getDescription());
        return project;
    }

}
