package com.makochanov.projectmanagement.service.impl;

import com.makochanov.projectmanagement.exception.ResourceNotFoundException;
import com.makochanov.projectmanagement.exception.ResourceNotUpdatedException;
import com.makochanov.projectmanagement.model.dto.TaskDto;
import com.makochanov.projectmanagement.model.dto.UserCriteriaDto;
import com.makochanov.projectmanagement.model.dto.UserDto;
import com.makochanov.projectmanagement.model.entity.Task;
import com.makochanov.projectmanagement.model.entity.User;
import com.makochanov.projectmanagement.repository.UserRepository;
import com.makochanov.projectmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ConversionService conversionService;

    @Override
    public List<UserDto> findByCriteria(UserCriteriaDto dto) {
        return null;
    }

    @Override
    public UserDto findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        User userItem = user.orElseThrow(
                () -> new ResourceNotFoundException("Cannot find user resource with id " + userId));
        return conversionService.convert(userItem, UserDto.class);
    }

    @Override
    public UserDto delete(Long userId) {
        int updatedRows = userRepository.setDeletedStatus(userId, true);
        if (updatedRows == 0) {
            throw new ResourceNotUpdatedException("Cannot update delete status of user with id " + userId);
        }
        return findById(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

}
