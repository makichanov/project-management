package com.makichanov.projectmanagement.service.impl;

import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.service.TaskService;
import com.makichanov.projectmanagement.exception.ResourceNotFoundException;
import com.makichanov.projectmanagement.exception.ResourceNotUpdatedException;
import com.makichanov.projectmanagement.model.dto.CreatingTaskDto;
import com.makichanov.projectmanagement.model.entity.Project;
import com.makichanov.projectmanagement.model.entity.Task;
import com.makichanov.projectmanagement.repository.ProjectRepository;
import com.makichanov.projectmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ConversionService conversionService;

    @Override
    public List<TaskDto> findByProjectId(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        Project projectItem = project.orElseThrow(
                () -> new ResourceNotFoundException("Cannot find project resource with id " + projectId));
        List<Task> tasks = taskRepository.findByProject(projectItem);
        return TaskDto.toTaskDtoList(tasks);
    }

    @Override
    public Optional<TaskDto> findById(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        return task.map(t -> conversionService.convert(t, TaskDto.class));
    }

    @Override
    @Transactional
    public TaskDto create(CreatingTaskDto dto) {
        Task task = conversionService.convert(dto, Task.class);
        Optional<Project> project = projectRepository.findById(dto.getProjectId());
        Project projectItem = project.orElseThrow(
                () -> new ResourceNotFoundException("Project not found, id " + dto.getProjectId()));
        task.setProject(projectItem);
        Task createdTask = taskRepository.save(task);
        return conversionService.convert(createdTask, TaskDto.class);
    }

    @Override
    @Transactional
    public Optional<TaskDto> completeTask(Long taskId) {
        int updatedRows = taskRepository.setCompletedStatus(taskId, true);
        if (updatedRows == 0) {
            throw new ResourceNotUpdatedException("Cannot update close status of task with id " + taskId);
        }
        Optional<Task> task = taskRepository.findById(taskId);
        return task.map(t -> conversionService.convert(t, TaskDto.class));
    }

    @Override
    public Optional<TaskDto> deleteTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        taskRepository.deleteById(taskId);
        return task.map(t -> conversionService.convert(t, TaskDto.class));
    }
}
