package com.makichanov.projectmanagement.service.impl;

import com.makichanov.projectmanagement.exception.ResourceNotFoundException;
import com.makichanov.projectmanagement.model.dto.UserCriteriaDto;
import com.makichanov.projectmanagement.model.dto.UserDto;
import com.makichanov.projectmanagement.model.entity.User;
import com.makichanov.projectmanagement.service.UserService;
import com.makichanov.projectmanagement.exception.ResourceNotUpdatedException;
import com.makichanov.projectmanagement.model.dto.CreatingUserDto;
import com.makichanov.projectmanagement.model.entity.Role;
import com.makichanov.projectmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ConversionService conversionService;
    private final PasswordEncoder passwordEncoder;

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
    public UserDto create(CreatingUserDto dto) {
        User user = conversionService.convert(dto, User.class);
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User createdUser = userRepository.save(user);
        return conversionService.convert(createdUser, UserDto.class);
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
