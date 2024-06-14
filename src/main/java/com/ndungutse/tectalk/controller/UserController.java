package com.ndungutse.tectalk.controller;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndungutse.tectalk.config.jwt.JwtUtils;
import com.ndungutse.tectalk.dto.SigninRequest;
import com.ndungutse.tectalk.dto.SigninResponse;
import com.ndungutse.tectalk.dto.SignupResponse;
import com.ndungutse.tectalk.dto.UserDto;
import com.ndungutse.tectalk.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody UserDto userDto) {
        UserDto newUserDto = service.createUser(userDto);

        SignupResponse signupResponse = new SignupResponse();
        signupResponse.setId(newUserDto.getId());
        signupResponse.setName(newUserDto.getName());
        signupResponse.setEmail(newUserDto.getEmail());

        return new ResponseEntity<>(signupResponse, HttpStatus.CREATED);
    }

    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody SigninRequest credentials) {
        Authentication authentication;

        // Creates a authentication object with username and password which is not
        // authenticated
        authentication = new UsernamePasswordAuthenticationToken(credentials
                .getEmail(),
                credentials.getPassword());

        // Checks if cridentials match to create an authenticated object containing
        // principle, authorities, etc.
        authentication = authenticationManager.authenticate(authentication);

        // Setsup security contect
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Gets Principle
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Create Token
        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(auth -> auth.getAuthority())
                .collect(Collectors.toList());

        SigninResponse signinResponse = new SigninResponse(jwtToken,
                userDetails.getUsername(), roles);

        return new ResponseEntity<>(signinResponse, HttpStatus.CREATED);
    }

}
