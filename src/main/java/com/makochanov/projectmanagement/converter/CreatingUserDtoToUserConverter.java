package com.makochanov.projectmanagement.converter;

import com.makochanov.projectmanagement.model.dto.CreatingUserDto;
import com.makochanov.projectmanagement.model.entity.User;
import org.springframework.core.convert.converter.Converter;

public class CreatingUserDtoToUserConverter implements Converter<CreatingUserDto, User> {

    @Override
    public User convert(CreatingUserDto source) {
        User user = new User();
        user.setUsername(source.getUsername());
        return user;
    }

}
