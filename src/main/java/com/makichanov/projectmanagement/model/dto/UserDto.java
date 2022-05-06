package com.makichanov.projectmanagement.model.dto;

import lombok.Data;

@Data
public class UserDto {

    Long id;

    String username;

    private String role;

}
