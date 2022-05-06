package com.makichanov.projectmanagement.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProjectDto {

    Long id;

    String name;

    String description;

    Timestamp createDate;

    Boolean isDeleted;

    Boolean isClosed;

    List<TaskDto> tasks;

}
