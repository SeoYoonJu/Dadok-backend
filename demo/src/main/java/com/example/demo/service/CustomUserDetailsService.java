package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 사용자 정보 조회 (DB에서 찾는 로직)
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        if (user.getUsername() == null || user.getPassword() == null) {
//            throw new UsernameNotFoundException("Username or password is missing");
//        }
//
//        // 사용자 권한을 빈 리스트로 설정 (권한이 없을 경우)
//        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
//        if (authorities == null || authorities.isEmpty()) {
//            authorities = Collections.emptyList();
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                authorities // 권한
//        );
//    }
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUserId(username)  // userId로 검색하도록 수정
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
}

}