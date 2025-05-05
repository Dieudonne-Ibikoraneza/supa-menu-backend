package com.dieudonne.supa_menu.service;

import com.dieudonne.supa_menu.config.JwtUtil;
import com.dieudonne.supa_menu.model.Role;
import com.dieudonne.supa_menu.model.User;
import com.dieudonne.supa_menu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public User register(String fullName, String email, String phoneNumber, String password, Role role){

        if(userRepository.existsByEmail(email))
            throw new IllegalArgumentException("Email already exists");

        if(userRepository.existsByPhoneNumber(phoneNumber))
            throw new IllegalArgumentException("Phone number already exists");

        User user = User.builder()
                .fullName(fullName)
                .email(email)
                .phoneNumber(phoneNumber)
                .password(passwordEncoder.encode(password))
                .role(role)
                .createdAt(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }

    public String login(String email, String password){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new IllegalArgumentException("Invalid email or password");

        return jwtUtil.generateToken(user.getEmail(), user.getRole().name());
    }

    //TODO: Implement forgotPassword and resetPassword methods
}
