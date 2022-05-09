package com.makichanov.projectmanagement.service;

import com.github.javafaker.Faker;
import com.makichanov.projectmanagement.model.dto.CreatingTaskDto;
import com.makichanov.projectmanagement.model.dto.TaskDto;
import com.makichanov.projectmanagement.model.entity.Project;
import com.makichanov.projectmanagement.model.entity.Task;
import com.makichanov.projectmanagement.repository.ProjectRepository;
import com.makichanov.projectmanagement.repository.TaskRepository;
import com.makichanov.projectmanagement.repository.specifier.QueryBuilder;
import com.makichanov.projectmanagement.service.impl.ProjectServiceImpl;
import com.makichanov.projectmanagement.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TaskServiceTest {

    private final Faker faker = new Faker();

    private TaskServiceImpl taskService;
    @Autowired
    private ConversionService conversionService;
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    private MockitoSession mockitoSession;

    @BeforeEach
    public void beforeMethod() {
        projectRepository = mock(ProjectRepository.class);
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskServiceImpl(taskRepository, projectRepository, conversionService);
        mockitoSession = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @AfterEach
    public void afterMethod() {
        mockitoSession.finishMocking();
    }

    @Test
    void findByProjectId() {
        Long pId = faker.number().randomNumber();
        Project p = new Project();
        p.setId(pId);
        when(projectRepository.findById(pId))
                .thenReturn(Optional.of(p));
        Task t1 = new Task();
        t1.setName(faker.name().name());
        t1.setDescription(faker.name().name());
        t1.setProject(p);
        Task t2 = new Task();
        t2.setName(faker.name().name());
        t2.setDescription(faker.name().name());
        t2.setProject(p);
        List<Task> tasks = new ArrayList<>();
        Collections.addAll(tasks, t1, t2);
        when(taskRepository.findByProject(p))
                .thenReturn(tasks);
        List<TaskDto> expected = TaskDto.toTaskDtoList(tasks);
        List<TaskDto> actual = taskService.findByProjectId(pId);
        assertEquals(expected, actual);
        verify(projectRepository, times(1)).findById(pId);
        verify(taskRepository, times(1)).findByProject(p);
    }

    @Test
    void findById() {
        Long id = faker.number().randomNumber();
        Task t = new Task();
        t.setId(id);
        t.setName(faker.name().name());
        t.setDescription(faker.name().name());
        t.setProject(new Project());
        when(taskRepository.findById(id))
                .thenReturn(Optional.of(t));
        TaskDto expected = conversionService.convert(t, TaskDto.class);
        TaskDto actual = taskService.findById(id).get();
        assertEquals(expected, actual);
        verify(taskRepository, times(1)).findById(id);
    }

    @Test
    void create() {
        /*Long pId = faker.number().randomNumber();
        Project p = new Project();
        p.setId(pId);
        when(projectRepository.findById(pId))
                .thenReturn(Optional.of(p));
        CreatingTaskDto dto = new CreatingTaskDto();
        dto.setName(faker.name().name());
        dto.setDescription(faker.name().name());
        dto.setProjectId(pId);
        Long tId = faker.number().randomNumber();
        String tName = faker.name().name();
        String tDescription = faker.name().name();
        Task t = new Task();
        t.setName(tName);
        t.setDescription(tDescription);
        t.setProject(p);
        Task tSaved = new Task();
        tSaved.setId(tId);
        tSaved.setName(tName);
        tSaved.setDescription(tDescription);
        tSaved.setProject(p);
        when(taskRepository.save(t))
                .thenReturn(tSaved);
        TaskDto expected = conversionService.convert(tSaved, TaskDto.class);
        TaskDto actual = taskService.create(dto);
        assertEquals(expected, actual);
        verify(projectRepository, times(1)).findById(pId);
        verify(taskRepository, times(1)).save(t);*/
    }

    @Test
    void completeTask() {
    }

    @Test
    void deleteTask() {
    }
}