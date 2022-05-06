package com.makichanov.projectmanagement.service;

import com.makichanov.projectmanagement.model.dto.CreatingUserDto;
import com.makichanov.projectmanagement.model.dto.UserCriteriaDto;
import com.makichanov.projectmanagement.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findByCriteria(UserCriteriaDto dto);

    UserDto findById(Long userId);

    UserDto create(CreatingUserDto dto);

    UserDto delete(Long userId);

}
