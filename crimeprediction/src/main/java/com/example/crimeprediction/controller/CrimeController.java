package com.example.crimeprediction.controller;

import com.example.crimeprediction.model.CrimeRecord;
import com.example.crimeprediction.service.CrimeAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/crimes")
@CrossOrigin(origins = "*")
public class CrimeController {

    @Autowired
    private CrimeAnalyticsService service;

    @PostMapping
    public CrimeRecord createCrime(@RequestBody CrimeRecord crime) {
        return service.addCrime(crime);
    }

    @GetMapping
    public List<CrimeRecord> getAllCrimes() {
        return service.getAllCrimes();
    }

    @GetMapping("/filter")
    public List<CrimeRecord> filterCrimes(
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return service.filterCrimes(area, type, start, end);
    }

    @GetMapping("/risk-zones")
    public Map<String, Long> getRiskZones() {
        return service.getRiskZones();
    }
}
