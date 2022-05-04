package com.makochanov.projectmanagement.model.dto;

import com.makochanov.projectmanagement.converter.TaskToTaskDtoConverter;
import com.makochanov.projectmanagement.model.entity.Project;
import com.makochanov.projectmanagement.model.entity.Task;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class TaskDto {

    Long id;

    String name;

    String description;

    Boolean isCompleted;

    Timestamp createDate;

    Timestamp completionDate;

    Boolean isDeleted;

    ProjectDto project;

    public static List<TaskDto> toTaskDtoList(Collection<Task> tasks) {
        TaskToTaskDtoConverter converter = new TaskToTaskDtoConverter();
        return tasks.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

}
