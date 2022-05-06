package com.makichanov.projectmanagement.model.dto;

import lombok.Data;

@Data
public class CreatingTaskDto {

    String name;

    String description;

    Long projectId;

}
