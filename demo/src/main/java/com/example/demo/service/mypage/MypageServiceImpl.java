package com.example.demo.service.mypage;


import com.example.demo.domain.User;
import com.example.demo.dto.mypage.ProfileResponseDTO;
import com.example.demo.repository.ReportRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Override
    public ProfileResponseDTO showMyProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        return ProfileResponseDTO.from(user);

    }
    @Override
    public Long calculateProgress(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Long reportCount = reportRepository.countByUserId(userId);
        Long goal = user.getGoal();

        if (goal == 0) {
            throw new RuntimeException("목표 설정 없음");
        }
        return (reportCount * 100) / goal;
    }



}
