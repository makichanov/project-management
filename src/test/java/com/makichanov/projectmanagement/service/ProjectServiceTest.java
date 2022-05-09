package com.makichanov.projectmanagement.service;

import com.github.javafaker.Faker;
import com.makichanov.projectmanagement.model.dto.CreatingProjectDto;
import com.makichanov.projectmanagement.model.dto.ProjectDto;
import com.makichanov.projectmanagement.model.entity.Project;
import com.makichanov.projectmanagement.repository.ProjectRepository;
import com.makichanov.projectmanagement.repository.specifier.QueryBuilder;
import com.makichanov.projectmanagement.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProjectServiceTest {

    private final Faker faker = new Faker();

    private ProjectServiceImpl projectService;
    @Autowired
    private ConversionService conversionService;
    private ProjectRepository projectRepository;
    private QueryBuilder queryBuilder;

    private MockitoSession mockitoSession;

    @BeforeEach
    public void beforeMethod() {
        projectRepository = mock(ProjectRepository.class);
        queryBuilder = mock(QueryBuilder.class);
        projectService = new ProjectServiceImpl(projectRepository, conversionService, queryBuilder);
        mockitoSession = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @AfterEach
    public void afterMethod() {
        mockitoSession.finishMocking();
    }

    @Test
    void findById() {
        Long id = faker.number().randomNumber();
        Project p = new Project();
        p.setName(faker.name().name());
        p.setDescription(faker.name().name());
        p.setId(id);
        when(projectRepository.findById(id))
                .thenReturn(Optional.of(p));
        ProjectDto expected = conversionService.convert(p, ProjectDto.class);
        ProjectDto actual = projectService.findById(id).get();
        assertEquals(expected, actual);
        verify(projectRepository, times(1)).findById(id);
    }

    @Test
    void create() {
        Long id = faker.number().randomNumber();
        String name = faker.name().name();
        String description = faker.name().name();
        CreatingProjectDto dto = new CreatingProjectDto();
        dto.setName(name);
        dto.setDescription(description);
        Project p = new Project();
        p.setName(name);
        p.setDescription(description);
        Project pSaved = new Project();
        pSaved.setName(name);
        pSaved.setDescription(description);
        pSaved.setId(id);
        when(projectRepository.save(p))
                .thenReturn(pSaved);
        ProjectDto expected = conversionService.convert(pSaved, ProjectDto.class);
        ProjectDto actual = projectService.create(dto);
        assertEquals(expected, actual);
        verify(projectRepository, times(1)).save(p);
    }

    @Test
    void closeProject() {
        Long id = faker.number().randomNumber();
        String name = faker.name().name();
        String description = faker.name().name();
        Project p = new Project();
        p.setName(faker.name().name());
        p.setDescription(faker.name().name());
        p.setId(id);
        p.setIsClosed(true);
        when(projectRepository.setClosedStatus(id, true))
                .thenReturn(1);
        when(projectRepository.findById(id))
                .thenReturn(Optional.of(p));
        ProjectDto expected = conversionService.convert(p, ProjectDto.class);
        ProjectDto actual = projectService.closeProject(id).get();
        assertEquals(expected, actual);
        verify(projectRepository, times(1)).setClosedStatus(id, true);
        verify(projectRepository, times(1)).findById(id);
    }

    @Test
    void deleteProject() {
        Long id = faker.number().randomNumber();
        String name = faker.name().name();
        String description = faker.name().name();
        Project p = new Project();
        p.setName(faker.name().name());
        p.setDescription(faker.name().name());
        p.setId(id);
        p.setIsDeleted(true);
        when(projectRepository.findById(id))
                .thenReturn(Optional.of(p));
        ProjectDto expected = conversionService.convert(p, ProjectDto.class);
        ProjectDto actual = projectService.deleteProject(id).get();
        assertEquals(expected, actual);
        verify(projectRepository, times(1)).deleteById(id);
        verify(projectRepository, times(1)).findById(id);
    }
}