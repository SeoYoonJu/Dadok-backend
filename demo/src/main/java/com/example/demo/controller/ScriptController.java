package com.example.demo.controller;

import com.example.demo.dto.script.ScriptResponseDTO;
import com.example.demo.service.script.ScriptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scripts")
@RequiredArgsConstructor
public class ScriptController {

    private final ScriptService scriptService;

    @GetMapping("/today")
    public ResponseEntity<ScriptResponseDTO> getScriptsForToday() {
        ScriptResponseDTO scripts = scriptService.getScriptsForToday();
        return ResponseEntity.ok(scripts);
    }
}