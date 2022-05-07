package com.makichanov.projectmanagement.converter;

import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.model.entity.Task;
import org.springframework.core.convert.converter.Converter;

public class TaskToTaskDtoConverter implements Converter<Task, TaskDto> {

    @Override
    public TaskDto convert(Task source) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(source.getId());
        taskDto.setName(source.getName());
        taskDto.setDescription(source.getDescription());
        taskDto.setIsCompleted(source.getIsCompleted());
        taskDto.setCreateDate(source.getCreateDate());
        taskDto.setCompletionDate(source.getCompletionDate());
        taskDto.setIsDeleted(source.getIsDeleted());
        taskDto.setProjectId(source.getProject().getId());
        return taskDto;
    }

}
