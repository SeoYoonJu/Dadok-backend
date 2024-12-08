package com.example.demo.controller;

import com.example.demo.apipayload.ApiResponse;
import com.example.demo.domain.User;
import com.example.demo.dto.mypage.ProfileResponseDTO;
import com.example.demo.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> showProfile(@AuthenticationPrincipal User user){
        ProfileResponseDTO Profile = mypageService.showMyProfile(user.getId());
        return ResponseEntity.ok(ApiResponse.onSuccess(Profile));
    }

    @GetMapping("/percent")
    public ResponseEntity<ApiResponse<Long>> showPercent(@AuthenticationPrincipal User user){
        Long percent = mypageService.calculateProgress(user.getId());
        return ResponseEntity.ok(ApiResponse.onSuccess(percent));
    }

}
