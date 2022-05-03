package com.makochanov.projectmanagement.service;

import com.makochanov.projectmanagement.model.dto.UserCriteriaDto;
import com.makochanov.projectmanagement.model.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> findByCriteria(UserCriteriaDto dto);

    UserDto findById(Long userId);

    UserDto delete(Long userId);

}
