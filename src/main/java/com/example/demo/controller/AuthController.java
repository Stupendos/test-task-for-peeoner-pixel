package com.example.demo.controller;

import com.example.demo.component.JwtUtil;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String emailOrPhone, @RequestParam String password) {
        User user = userRepository.findByEmailOrPhone(emailOrPhone);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtUtil.generateToken(user.getId());
        return ResponseEntity.ok(token);
    }
}
