package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.dto.user.UserJoinRequestDTO;
import com.example.demo.dto.user.UserLoginDTO;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 가입 처리
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequestDTO userJoinRequestDto) {
        userService.registerUser(userJoinRequestDto);
        return ResponseEntity.ok("Registration successful");
    }

    // 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userLoginDto) {
        try {
            String token = userService.login(userLoginDto.getUserId(), userLoginDto.getUserPassword());
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Invalid password");
        }
    }
}
