package com.makichanov.projectmanagement.converter;

import com.makichanov.projectmanagement.model.dto.UserDto;
import com.makichanov.projectmanagement.model.entity.User;
import org.springframework.core.convert.converter.Converter;

public class UserToUserDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        UserDto userDto = new UserDto();
        userDto.setUsername(source.getUsername());
        userDto.setId(source.getId());
        userDto.setRole(source.getRole().getName());
        return userDto;
    }

}
