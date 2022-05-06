package com.makichanov.projectmanagement.converter;

import com.makichanov.projectmanagement.model.entity.Project;
import com.makichanov.projectmanagement.model.dto.CreatingProjectDto;
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
