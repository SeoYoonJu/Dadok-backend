package com.example.demo.service.user;

import com.example.demo.domain.User;
import com.example.demo.dto.user.UserJoinRequestDTO;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void registerUser(UserJoinRequestDTO userJoinRequestDto) {
        User newUser = userJoinRequestDto.toEntity(passwordEncoder);
        userRepository.save(newUser);
    }
    @Override
    public String login(String userId, String userPassword) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            throw new RuntimeException("Invalid password");
        }

        List<String> roles = Collections.singletonList("ROLE_USER"); // 또는 DB에서 roles를 조회

        return jwtTokenProvider.createToken(userId, roles);
    }
}
