package com.example.demo.service;

import com.example.demo.domain.Book;

import com.example.demo.domain.User;
import com.example.demo.dto.book.BookResponseDTO;
import com.example.demo.dto.mypage.ProfileResponseDTO;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final UserRepository userRepository;

    public ProfileResponseDTO showMyProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        // Book 엔티티를 BookResponseDTO로 변환하여 반환
        return ProfileResponseDTO.from(user);
    }

}
