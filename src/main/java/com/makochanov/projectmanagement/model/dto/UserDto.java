package com.makochanov.projectmanagement.model.dto;

import com.makochanov.projectmanagement.model.entity.Role;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String username;

    private Role role;

}
