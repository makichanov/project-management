package com.makichanov.projectmanagement.service.impl;

import com.makichanov.projectmanagement.exception.ResourceNotFoundException;
import com.makichanov.projectmanagement.model.dto.UserCriteriaDto;
import com.makichanov.projectmanagement.model.dto.UserDto;
import com.makichanov.projectmanagement.model.entity.User;
import com.makichanov.projectmanagement.repository.RoleRepository;
import com.makichanov.projectmanagement.service.UserService;
import com.makichanov.projectmanagement.exception.ResourceNotUpdatedException;
import com.makichanov.projectmanagement.model.dto.CreatingUserDto;
import com.makichanov.projectmanagement.model.entity.Role;
import com.makichanov.projectmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String DEFAULT_USER_ROLE = "ROLE_USER";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ConversionService conversionService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll(UserCriteriaDto dto) {
        Page<User> users = userRepository.findAll(PageRequest.of(dto.getPageNum().intValue(),
                dto.getPageSize().intValue()));
        return UserDto.toUserDtoList(users.getContent());
    }

    @Override
    public Optional<UserDto> findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(u -> conversionService.convert(u, UserDto.class));
    }

    @Override
    @Transactional
    public UserDto create(CreatingUserDto dto) {
        User user = conversionService.convert(dto, User.class);
        Role role = loadUserRole(DEFAULT_USER_ROLE);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User createdUser = userRepository.save(user);
        return conversionService.convert(createdUser, UserDto.class);
    }

    @Override
    public Optional<UserDto> delete(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        userRepository.deleteById(userId);
        return user.map(u -> conversionService.convert(u, UserDto.class));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    private Role loadUserRole(String roleName) {
        return roleRepository.findByName(roleName);
    }

}
