package com.makochanov.projectmanagement.model.dto;

import com.makochanov.projectmanagement.model.entity.Task;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProjectDto {

    private Long id;

    private String name;

    private String description;

    private Timestamp createDate;

    private Boolean isDeleted;

    private List<Task> tasks;

}
