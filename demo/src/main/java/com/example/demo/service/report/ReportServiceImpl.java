package com.example.demo.service.report;

import com.example.demo.domain.Report;
import com.example.demo.domain.User;
import com.example.demo.dto.report.ReportRequestDTO;
import com.example.demo.dto.report.ReportResponseDTO;
import com.example.demo.repository.ReportRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.report.ReportService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public ReportResponseDTO writeMyReport(String username, ReportRequestDTO request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Report report = request.toEntity(user);

        Report savedReport = reportRepository.save(report);

        return ReportResponseDTO.from(savedReport);
    }

    @Override
    @Transactional
    public ReportResponseDTO modifyMyReport(Long reportId, String username, ReportRequestDTO request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("독후감 없음"));

        if (!report.getUser().equals(user)) {
            throw new RuntimeException("수정 권한 없음");
        }

        report.setContent(request.getContent());

        Report updatedReport = reportRepository.save(report);

        return ReportResponseDTO.from(updatedReport);
    }

    @Override
    public ReportResponseDTO showMyReport(String username, Long reportId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Report report = reportRepository.findByReportId(reportId)
                .orElseThrow(() -> new RuntimeException("해당 책 없음"));

        if (!report.getUser().equals(user)) {
            throw new RuntimeException("조회 권한 없음");
        }

        return ReportResponseDTO.from(report);
    }

    @Override
    public List<ReportResponseDTO> showAllReports(User user) {

        List<Report> reports = reportRepository.findByUser(user);

        return reports.stream()
                .map(ReportResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMyReport(Long userId, Long reportId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Report report = reportRepository.findByReportId(reportId)
                .orElseThrow(() -> new RuntimeException("해당 책 없음"));

        if (!report.getUser().equals(user)) {
            throw new RuntimeException("삭제 권한 없음");
        }

        reportRepository.delete(report);
    }
}
