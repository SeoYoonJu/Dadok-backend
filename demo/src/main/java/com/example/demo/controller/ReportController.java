package com.example.demo.controller;

import com.example.demo.apipayload.ApiResponse;
import com.example.demo.domain.User;
import com.example.demo.dto.report.ReportRequestDTO;
import com.example.demo.dto.report.ReportResponseDTO;
import com.example.demo.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        System.out.println(authentication);
        return null;  // 인증되지 않은 경우 null 반환
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<ReportResponseDTO>> writeReport(@RequestBody ReportRequestDTO request) {
        String username = getCurrentUsername();
        ReportResponseDTO written = reportService.writeMyReport(username, request);

        return ResponseEntity.ok(ApiResponse.onSuccess(written));
    }

    @PatchMapping("/{reportId}")
    public ResponseEntity<ApiResponse<ReportResponseDTO>> modifyReport(@PathVariable Long reportId,@RequestBody ReportRequestDTO request){
        String username = getCurrentUsername();
        ReportResponseDTO modified = reportService.modifyMyReport(reportId, username, request);

        return ResponseEntity.ok(ApiResponse.onSuccess(modified));
    }


    @GetMapping("/{reportId}")
    public ResponseEntity<ApiResponse<ReportResponseDTO>> showReport(@PathVariable Long reportId){
        String username = getCurrentUsername();
        ReportResponseDTO eachReport = reportService.showMyReport(username, reportId);

        return ResponseEntity.ok(ApiResponse.onSuccess(eachReport));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<ReportResponseDTO>>> showAllReport(@AuthenticationPrincipal User user){
        List<ReportResponseDTO> allReports = reportService.showAllReports(user);

        return ResponseEntity.ok(ApiResponse.onSuccess(allReports));
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<ApiResponse<String>> deleteReport(@AuthenticationPrincipal User user, @PathVariable Long reportId){
        reportService.deleteMyReport(user.getId(),reportId);

        return ResponseEntity.ok(ApiResponse.onSuccess("삭제완료"));
    }
}
