package com.makichanov.projectmanagement.security.impl;

import com.makichanov.projectmanagement.model.dto.CreatingUserDto;
import com.makichanov.projectmanagement.security.AuthenticationService;
import com.makichanov.projectmanagement.security.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public String authenticate(CreatingUserDto dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return tokenService.generateToken(userDetails.getUsername(), userDetails.getPassword());
    }

}
