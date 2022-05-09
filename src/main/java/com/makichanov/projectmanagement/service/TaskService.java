package com.makichanov.projectmanagement.service;

import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.model.dto.CreatingTaskDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<TaskDto> findByProjectId(Long projectId);

    Optional<TaskDto> findById(Long taskId);

    TaskDto create(CreatingTaskDto dto);

    Optional<TaskDto> completeTask(Long taskId);

    Optional<TaskDto> deleteTask(Long taskId);

}
