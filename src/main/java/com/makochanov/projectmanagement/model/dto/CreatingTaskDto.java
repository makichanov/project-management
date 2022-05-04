package com.makochanov.projectmanagement.model.dto;

import lombok.Data;

@Data
public class CreatingTaskDto {

    String name;

    String description;

    Long projectId;

}
