package com.makichanov.projectmanagement.service;

import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.model.dto.CreatingTaskDto;

import java.util.List;
import java.util.Optional;

/**
 * The interface Task service.
 */
public interface TaskService {

    /**
     * Find by project id list.
     *
     * @param projectId the project id
     * @return the list
     */
    List<TaskDto> findByProjectId(Long projectId);

    /**
     * Find by id optional.
     *
     * @param taskId the task id
     * @return the optional
     */
    Optional<TaskDto> findById(Long taskId);

    /**
     * Create task dto.
     *
     * @param dto the dto
     * @return the task dto
     */
    TaskDto create(CreatingTaskDto dto);

    /**
     * Complete task optional.
     *
     * @param taskId the task id
     * @return the optional
     */
    Optional<TaskDto> completeTask(Long taskId);

    /**
     * Delete task optional.
     *
     * @param taskId the task id
     * @return the optional
     */
    Optional<TaskDto> deleteTask(Long taskId);

}
