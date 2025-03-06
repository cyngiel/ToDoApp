package com.ps.todoapp.security.controller;

import com.ps.todoapp.entity.user.UserDto;
import com.ps.todoapp.security.AuthTokenResponse;
import com.ps.todoapp.entity.user.User;
import com.ps.todoapp.security.service.JWTService;
import com.ps.todoapp.service.UserAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserAuthenticationController {

    private JWTService jwtService;
    private UserAuthenticationService userAuthenticationService;


    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserDto userDto) {
        User user = userAuthenticationService.signup(userDto);
        if (user == null) {
            throw new ResponseStatusException(CONFLICT, "User already exists");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthTokenResponse> authenticate(@RequestBody UserDto userDto) {
        User user = userAuthenticationService.authenticate(userDto);
        String jwt = jwtService.generateToken(user);

        AuthTokenResponse response = new AuthTokenResponse(jwt, jwtService.getExpirationTime());

        return ResponseEntity.ok(response);
    }
}