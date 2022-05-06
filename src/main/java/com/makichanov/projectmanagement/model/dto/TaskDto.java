package com.makichanov.projectmanagement.model.dto;

import com.makichanov.projectmanagement.converter.TaskToTaskDtoConverter;
import com.makichanov.projectmanagement.model.entity.Task;
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
