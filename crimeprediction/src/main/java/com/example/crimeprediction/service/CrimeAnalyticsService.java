package com.example.crimeprediction.service;

import com.example.crimeprediction.model.CrimeRecord;
import com.example.crimeprediction.dao.CrimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CrimeAnalyticsService {

    @Autowired
    private CrimeRecordRepository repository;

    public CrimeRecord addCrime(CrimeRecord crime) {
        return repository.save(crime);
    }

    public List<CrimeRecord> getAllCrimes() {
        return repository.findAll();
    }

    public List<CrimeRecord> filterCrimes(String area, String type, LocalDateTime start, LocalDateTime end) {
        List<CrimeRecord> crimes = repository.findAll();

        return crimes.stream()
                .filter(c -> (area == null || c.getArea().equalsIgnoreCase(area)) &&
                             (type == null || c.getCrimeType().equalsIgnoreCase(type)) &&
                             (start == null || !c.getTimestamp().isBefore(start)) &&
                             (end == null || !c.getTimestamp().isAfter(end)))
                .collect(Collectors.toList());
    }

    public Map<String, Long> getRiskZones() {
        List<CrimeRecord> crimes = repository.findAll();
        return crimes.stream()
                .filter(c -> c.getArea() != null)
                .collect(Collectors.groupingBy(CrimeRecord::getArea, Collectors.counting()));
    }
}
