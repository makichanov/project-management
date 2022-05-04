package com.makochanov.projectmanagement.service;

import com.makochanov.projectmanagement.model.dto.CreatingTaskDto;
import com.makochanov.projectmanagement.model.dto.TaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> findByProjectId(Long projectId);

    TaskDto findById(Long taskId);

    TaskDto create(CreatingTaskDto dto);

    TaskDto closeTask(Long taskId);

    TaskDto deleteTask(Long taskId);

}
