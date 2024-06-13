package com.ndungutse.tectalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndungutse.tectalk.dto.SignupResponse;
import com.ndungutse.tectalk.dto.UserDto;
import com.ndungutse.tectalk.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("signup")
    public ResponseEntity<SignupResponse> signUp(@RequestBody UserDto userDto) {
        UserDto newUserDto = service.createUser(userDto);

        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setId(newUserDto.getId());
        signupResponse.setName(newUserDto.getName());
        signupResponse.setEmail(newUserDto.getEmail());

        return new ResponseEntity<>(signupResponse, HttpStatus.CREATED);
    }

}
