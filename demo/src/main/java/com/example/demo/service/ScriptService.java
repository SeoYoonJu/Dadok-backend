package com.example.demo.service;
import com.example.demo.domain.Script;
import com.example.demo.dto.script.ScriptResponseDTO;
import com.example.demo.repository.ScriptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;



@Service
@RequiredArgsConstructor
public class ScriptService{

    private final ScriptRepository scriptRepository;

    public ScriptResponseDTO getScriptsForToday() {
        Date currentDate = new Date(); // 현재 날짜와 시간

        Script scripts = scriptRepository.findByDate(currentDate)
                .orElseThrow(() -> new RuntimeException("해당 날짜 글 없음"));

        return ScriptResponseDTO.from(scripts);
    }

}