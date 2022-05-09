package com.makichanov.projectmanagement.security;

import com.makichanov.projectmanagement.model.dto.CreatingUserDto;

/**
 * The interface Authentication service.
 */
public interface AuthenticationService {

    /**
     * Authenticate string.
     *
     * @param dto the dto
     * @return the string
     */
    String authenticate(CreatingUserDto dto);

}
