package com.makichanov.projectmanagement.service;

import com.makichanov.projectmanagement.model.dto.CreatingUserDto;
import com.makichanov.projectmanagement.model.dto.UserCriteriaDto;
import com.makichanov.projectmanagement.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> findAll(UserCriteriaDto dto);

    Optional<UserDto> findById(Long userId);

    UserDto create(CreatingUserDto dto);

    Optional<UserDto> delete(Long userId);

}
