package com.harshitha.freelancemanager.service;

import com.harshitha.freelancemanager.dto.LoginRequestDto;
import com.harshitha.freelancemanager.dto.RegisterRequestDto;
import com.harshitha.freelancemanager.entity.User;
import com.harshitha.freelancemanager.repository.UserRepository;
import com.harshitha.freelancemanager.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(RegisterRequestDto dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return "Email already exists";
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        userRepository.save(user);

        return "User registered successfully";
    }

    public String login(LoginRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (user == null) {
            return "User not found";
        }

        if (!user.getPassword().equals(dto.getPassword())) {
            return "Invalid password";
        }

        return jwtUtil.generateToken(user.getId());
    }
}