package com.makichanov.projectmanagement.security;

import com.makichanov.projectmanagement.model.dto.CreatingUserDto;

public interface AuthenticationService {

    String authenticate(CreatingUserDto dto);

}
