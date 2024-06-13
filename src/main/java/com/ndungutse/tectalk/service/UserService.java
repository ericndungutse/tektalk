package com.ndungutse.tectalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ndungutse.tectalk.dto.UserDto;
import com.ndungutse.tectalk.model.User;
import com.ndungutse.tectalk.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // Create Post
    public UserDto createUser(UserDto userDto) {
        // Convert DTO to User
        User user = mapToUser(userDto);

        // Persist into the db
        User newUser = repository.save(user);

        // Convert Post to DTO
        UserDto userResponse = mapToDto(newUser);

        return userResponse;
    }

    // Mappers
    private User mapToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }

    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;

    }
}
