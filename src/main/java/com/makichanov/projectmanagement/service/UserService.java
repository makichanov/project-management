package com.makichanov.projectmanagement.service;

import com.makichanov.projectmanagement.model.dto.CreatingUserDto;
import com.makichanov.projectmanagement.model.dto.UserCriteriaDto;
import com.makichanov.projectmanagement.model.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Find all list.
     *
     * @param dto the dto
     * @return the list
     */
    List<UserDto> findAll(UserCriteriaDto dto);

    /**
     * Find by id optional.
     *
     * @param userId the user id
     * @return the optional
     */
    Optional<UserDto> findById(Long userId);

    /**
     * Create user dto.
     *
     * @param dto the dto
     * @return the user dto
     */
    UserDto create(CreatingUserDto dto);

    /**
     * Delete optional.
     *
     * @param userId the user id
     * @return the optional
     */
    Optional<UserDto> delete(Long userId);

}
