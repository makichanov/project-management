package com.makichanov.projectmanagement.controller;

import com.makichanov.projectmanagement.model.dto.UserCriteriaDto;
import com.makichanov.projectmanagement.model.dto.UserDto;
import com.makichanov.projectmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> read(@RequestBody UserCriteriaDto dto) {
        return userService.findAll(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        Optional<UserDto> user = userService.findById(id);
        return user.isPresent()
                ? new ResponseEntity<>(user.get(), HttpStatus.OK)
                : new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Optional<UserDto> user = userService.delete(id);
        return user.isPresent()
                ? new ResponseEntity<>(user.get(), HttpStatus.OK)
                : new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.NOT_FOUND);
    }

}
