package com.makochanov.projectmanagement.converter;

import com.makochanov.projectmanagement.model.dto.UserDto;
import com.makochanov.projectmanagement.model.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        UserDto userDto = new UserDto();
        userDto.setUsername(source.getUsername());
        userDto.setId(source.getId());
        userDto.setRole(source.getRole());
        return userDto;
    }
}
