package com.example.demo.service.script;
import com.example.demo.domain.Script;
import com.example.demo.dto.script.ScriptResponseDTO;
import com.example.demo.repository.ScriptRepository;
import com.example.demo.service.script.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    @Override
    public ScriptResponseDTO getScriptsForToday() {
        LocalDate currentDate =  LocalDate.now();

        Script scripts = scriptRepository.findByDate(currentDate)
                .orElseThrow(() -> new RuntimeException("해당 날짜 글 없음"));

        return ScriptResponseDTO.from(scripts);
    }

}