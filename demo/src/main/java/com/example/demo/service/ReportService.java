package com.example.demo.service;

import com.example.demo.domain.Book;
import com.example.demo.domain.Report;
import com.example.demo.domain.User;
import com.example.demo.dto.book.BookRequestDTO;
import com.example.demo.dto.book.BookResponseDTO;
import com.example.demo.dto.report.ReportRequestDTO;
import com.example.demo.dto.report.ReportResponseDTO;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ReportRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    @Transactional
    public ReportResponseDTO writeMyReport(String username, ReportRequestDTO request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Report report = request.toEntity(user);

        // Book 엔티티 저장
        Report savedReport = reportRepository.save(report);

        // Book을 BookResponseDTO로 변환하여 반환
        return ReportResponseDTO.from(savedReport);
    }

    @Transactional
    public ReportResponseDTO modifyMyReport(Long reportId, String username, ReportRequestDTO request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("독후감 없음"));

        if (!report.getUser().equals(user)) {
            throw new RuntimeException("수정 권한 없음");
        }
        // 내용 업데이트
        report.setContent(request.getContent());
        // 다이어리 저장
        Report updatedReport = reportRepository.save(report);

        return ReportResponseDTO.from(updatedReport);
    }

    public ReportResponseDTO showMyReport(String username, Long reportId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("유저 없음"));

        Report report = reportRepository.findByReportId(reportId)
                .orElseThrow(() -> new RuntimeException("해당 책 없음"));

        if (!report.getUser().equals(user)) {
            throw new RuntimeException("조회 권한 없음");
        }

        // Book 엔티티를 BookResponseDTO로 변환하여 반환
        return ReportResponseDTO.from(report);
    }

    public List<ReportResponseDTO> showAllReports() {
        // 모든 책(Book) 엔티티 조회
        List<Report> reports = reportRepository.findAll();

        if (reports.isEmpty()) {
            throw new RuntimeException("책이 존재하지 않습니다");
        }

        // 책(Book) 엔티티 리스트를 BookResponseDTO 리스트로 변환하여 반환
        return reports.stream()
                .map(ReportResponseDTO::from)
                .collect(Collectors.toList());
    }
}
