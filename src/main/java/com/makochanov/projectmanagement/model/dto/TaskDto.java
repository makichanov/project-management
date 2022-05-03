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

    private Long id;

    private String name;

    private String description;

    private Boolean isCompleted;

    private Timestamp createDate;

    private Timestamp completionDate;

    private Boolean isDeleted;

    private Project project;

    public static List<TaskDto> toTaskDtoList(Collection<Task> tasks) {
        TaskToTaskDtoConverter converter = new TaskToTaskDtoConverter();
        return tasks.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

}
