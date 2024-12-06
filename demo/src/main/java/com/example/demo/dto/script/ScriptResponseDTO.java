package com.example.demo.dto.script;

import com.example.demo.domain.Report;
import com.example.demo.domain.Script;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ScriptResponseDTO {
    private String content;
    private String author;
    ; // 예시로 userNickname을 포함할 수 있습니다. 필요에 따라 추가

    // Book 엔티티를 BookResponseDTO로 변환하는 정적 메서드
    public static ScriptResponseDTO from(Script script) {
            return new ScriptResponseDTO(
                    script.getContent(),
                    script.getAuthor()
            );
    }
}
