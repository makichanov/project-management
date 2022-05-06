package com.makichanov.projectmanagement.controller;

import com.makichanov.projectmanagement.model.dto.CreatingUserDto;
import com.makichanov.projectmanagement.model.dto.UserDto;
import com.makichanov.projectmanagement.security.AuthenticationService;
import com.makichanov.projectmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody CreatingUserDto dto) {
        return authenticationService.authenticate(dto);
    }

    @PostMapping(value = "/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto signIn(@RequestBody CreatingUserDto dto) {
        return userService.create(dto);
    }
}
