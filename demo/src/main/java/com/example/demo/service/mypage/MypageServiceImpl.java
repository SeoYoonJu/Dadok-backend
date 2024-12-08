package com.example.demo.service.mypage;


import com.example.demo.domain.User;
import com.example.demo.dto.mypage.ProfileResponseDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final UserRepository userRepository;

    @Override
    public ProfileResponseDTO showMyProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        return ProfileResponseDTO.from(user);
    }

}
