package com.pradyumn.payflow.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pradyumn.payflow.dto.LoginRequest;
import com.pradyumn.payflow.dto.LoginResponse;
import com.pradyumn.payflow.entity.User;
import com.pradyumn.payflow.repository.UserRepository;
import com.pradyumn.payflow.security.JwtUtil;

@Service
public class AuthService {

        private final UserRepository userRepository;
        private final JwtUtil jwtUtil;
        private final PasswordEncoder passwordEncoder;

        public AuthService(
                        UserRepository userRepository,
                        JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {

                this.userRepository = userRepository;
                this.jwtUtil = jwtUtil;
                this.passwordEncoder = passwordEncoder;
        }

        public LoginResponse login(
                        LoginRequest request) {

                User user = userRepository.findByEmail(
                                request.getEmail())
                                .orElseThrow(() -> new RuntimeException(
                                                "Invalid credentials"));

                if (!passwordEncoder.matches(
                                request.getPassword(),
                                user.getPassword())) {

                        throw new RuntimeException(
                                        "Invalid credentials");
                }

                String token = jwtUtil.generateToken(
                                user.getId(),
                                user.getEmail(),
                                user.getRole());

                return new LoginResponse(token);
        }
}