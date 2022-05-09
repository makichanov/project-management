package com.makichanov.projectmanagement.model.dto;

import com.makichanov.projectmanagement.converter.TaskToTaskDtoConverter;
import com.makichanov.projectmanagement.converter.UserToUserDtoConverter;
import com.makichanov.projectmanagement.model.entity.Task;
import com.makichanov.projectmanagement.model.entity.User;
import lombok.Data;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {

    Long id;

    String username;

    private String role;

    public static List<UserDto> toUserDtoList(Collection<User> users) {
        UserToUserDtoConverter converter = new UserToUserDtoConverter();
        return users.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

}
