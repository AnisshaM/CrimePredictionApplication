package com.example.crimeprediction.dao;

import com.example.crimeprediction.model.CrimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CrimeRecordRepository extends JpaRepository<CrimeRecord, Long> {
    List<CrimeRecord> findByArea(String area);
    List<CrimeRecord> findByCrimeType(String crimeType);
    List<CrimeRecord> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
