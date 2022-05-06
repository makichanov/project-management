package com.makichanov.projectmanagement.converter;

import com.makichanov.projectmanagement.model.dto.ProjectDto;
import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.model.entity.Project;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class ProjectToProjectDtoConverter implements Converter<Project, ProjectDto> {

    @Override
    public ProjectDto convert(Project source) {
       ProjectDto projectDto = new ProjectDto();
       projectDto.setId(source.getId());
       projectDto.setName(source.getName());
       projectDto.setDescription(source.getDescription());
       projectDto.setCreateDate(source.getCreateDate());
       projectDto.setIsDeleted(source.getIsDeleted());
        List<TaskDto> taskDtos = TaskDto.toTaskDtoList(source.getTasks());
       projectDto.setTasks(taskDtos);
       return projectDto;
    }

}
