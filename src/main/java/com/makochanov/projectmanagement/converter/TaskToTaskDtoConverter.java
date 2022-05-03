package com.makochanov.projectmanagement.converter;

import com.makochanov.projectmanagement.model.dto.TaskDto;
import com.makochanov.projectmanagement.model.entity.Task;
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
        taskDto.setProject(source.getProject());
        return taskDto;
    }

}
