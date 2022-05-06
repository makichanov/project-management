package com.makichanov.projectmanagement.converter;

import com.makichanov.projectmanagement.model.entity.User;
import com.makichanov.projectmanagement.model.dto.CreatingUserDto;
import org.springframework.core.convert.converter.Converter;

public class CreatingUserDtoToUserConverter implements Converter<CreatingUserDto, User> {

    @Override
    public User convert(CreatingUserDto source) {
        User user = new User();
        user.setUsername(source.getUsername());
        return user;
    }

}
