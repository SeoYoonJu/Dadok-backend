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


    public static ScriptResponseDTO from(Script script) {
            return new ScriptResponseDTO(
                    script.getContent(),
                    script.getAuthor()
            );
    }
}
