package com.example.demo.dto.report;

import com.example.demo.domain.Book;
import com.example.demo.domain.Report;
import com.example.demo.dto.book.BookResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportResponseDTO {
    private String title;
    private String content;
    private String user; // 예시로 userNickname을 포함할 수 있습니다. 필요에 따라 추가

    // Book 엔티티를 BookResponseDTO로 변환하는 정적 메서드
    public static ReportResponseDTO from(Report report) {
            return new ReportResponseDTO(
                report.getTitle(),
                report.getContent(),
                report.getUser().getUsername() // User의 닉네임을 응답에 포함할 경우
        );
    }
}
