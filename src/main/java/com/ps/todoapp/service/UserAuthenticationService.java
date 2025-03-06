package com.ps.todoapp.service;

import com.ps.todoapp.entity.user.User;
import com.ps.todoapp.entity.user.UserDto;
import com.ps.todoapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User signup(UserDto userDto) {
        User user = new User(userDto.username(), passwordEncoder.encode(userDto.password()));
        if (userRepository.findByUsername(userDto.username()).isPresent()) {
            return null;
        }
        return userRepository.save(user);
    }

    public User authenticate(UserDto userDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.username(), userDto.password()));

        return userRepository.findByUsername(userDto.username()).orElseThrow();
    }
}