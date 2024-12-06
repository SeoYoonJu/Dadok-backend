package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.user.UserJoinRequestDTO;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
//@Service
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public User registerUser(UserJoinRequestDTO userJoinRequestDto) {
//        // DTO에서 엔티티 생성
//        User newUser = userJoinRequestDto.toEntity(passwordEncoder);
//
//        // 데이터베이스에 사용자 저장
//        return userRepository.save(newUser);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        return userRepository.findByUserEmail(username)
//                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
//
//
//    }
//}
import com.example.demo.domain.User;
import com.example.demo.dto.user.UserJoinRequestDTO;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원 가입 처리
//    public void registerUser(UserJoinRequestDTO userJoinRequestDto) {
//        User newUser = userJoinRequestDto.toEntity(passwordEncoder);
//        newUser.setRoles(Collections.singletonList("ROLE_USER"));
//        userRepository.save(newUser);
//    }


    public void registerUser(UserJoinRequestDTO userJoinRequestDto) {
        User newUser = userJoinRequestDto.toEntity(passwordEncoder);
        userRepository.save(newUser);  // 역할 설정 부분 삭제
    }


    // 사용자 로그인 처리
    public String login(String userId, String userPassword) {
        // 사용자 조회
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 비밀번호 검증
        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // 사용자 권한 가져오기
        List<String> roles = Collections.singletonList("ROLE_USER"); // 또는 DB에서 roles를 조회

        // JWT 토큰 생성
        return jwtTokenProvider.createToken(userId, roles);
    }
}
