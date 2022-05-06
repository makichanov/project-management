package com.makichanov.projectmanagement.service;

import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.model.dto.CreatingTaskDto;

import java.util.List;

public interface TaskService {

    List<TaskDto> findByProjectId(Long projectId);

    TaskDto findById(Long taskId);

    TaskDto create(CreatingTaskDto dto);

    TaskDto completeTask(Long taskId);

    TaskDto deleteTask(Long taskId);

}
