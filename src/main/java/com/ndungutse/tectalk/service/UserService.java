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

    // Create
    public UserDto createUser(UserDto userDto) {
        // Convert DTO to User
        User user = mapToUser(userDto);

        User newUser = repository.save(user);
        // Convert Post to DTO
        UserDto userResponse = mapToDto(newUser);
        return userResponse;
    }

    // // signin
    // public SigninResponse singin(SigninRequest, credentials){

    // }

    // Mappers
    private User mapToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("USER");
        return user;
    }

    private UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        // userDto.setRole(user.getRole());
        return userDto;

    }
}
