package com.example.demo.service.report;

import com.example.demo.domain.User;
import com.example.demo.dto.report.ReportRequestDTO;
import com.example.demo.dto.report.ReportResponseDTO;

import java.util.List;

public interface ReportService {

    ReportResponseDTO writeMyReport(String username, ReportRequestDTO request);
    ReportResponseDTO modifyMyReport(Long reportId, String username, ReportRequestDTO request);
    ReportResponseDTO showMyReport(String username, Long reportId);
    List<ReportResponseDTO> showAllReports(User user);
    void deleteMyReport(Long userId, Long reportId);

}
