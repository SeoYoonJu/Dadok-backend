package com.example.demo.repository;

import com.example.demo.domain.Script;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScriptRepository extends JpaRepository<Script, Long> {
    Optional<Script> findByDate(LocalDate date);
}