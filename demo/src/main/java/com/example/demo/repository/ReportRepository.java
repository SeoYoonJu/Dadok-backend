package com.example.demo.repository;

import com.example.demo.domain.Report;
import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportId(Long reportId);
    List<Report> findByUser(User user);

    @Query("SELECT COUNT(r) FROM Report r WHERE r.user.id = :userId")
    Long countByUserId(@Param("userId") Long userId);
}
