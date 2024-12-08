package com.example.demo.dto.report;

import com.example.demo.domain.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String user;


    public static ReportResponseDTO from(Report report) {
            return new ReportResponseDTO(
                    report.getReportId(),
                    report.getTitle(),
                    report.getContent(),
                    report.getUser().getUsername()
        );
    }
}
