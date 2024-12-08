package com.example.demo.dto.report;

import com.example.demo.domain.Report;
import com.example.demo.domain.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportRequestDTO {
    private String title;
    private String content;

    public Report toEntity(User user) {
        return Report.builder()
                .user(user)
                .title(this.title)
                .content(this.content)
                .user(user)
                .build();
    }
}
