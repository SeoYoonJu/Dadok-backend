package com.example.demo.service.mypage;

import com.example.demo.dto.mypage.ProfileResponseDTO;

public interface MypageService {
    ProfileResponseDTO showMyProfile(Long userId);

}
