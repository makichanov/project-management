package com.makichanov.projectmanagement.model.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ProjectCriteriaDto {

    String name;

    String description;

    String sortBy;

    boolean orderDesc;

    @Min(value = 0, message = "Page size should be greater than or equal to zero")
    Long pageNum = 0L;

    @Min(value = 1, message = "Page size should be represented as positive number")
    @Max(value = 100, message = "Page size should not be greater than 100")
    Long pageSize = 15L;

}
