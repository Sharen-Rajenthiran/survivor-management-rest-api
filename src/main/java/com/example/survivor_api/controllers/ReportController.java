package com.example.survivor_api.controllers;

import com.example.survivor_api.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService service;

    public ReportController(ReportService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public ResponseEntity<?> summary() {
        return ResponseEntity.ok(service.getReports());
    }
}
