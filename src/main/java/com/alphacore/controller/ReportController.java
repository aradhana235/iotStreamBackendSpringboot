package com.alphacore.controller;

import com.alphacore.model.Report;
import com.alphacore.repository.ReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    // GET ALL
    @GetMapping
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public Optional<Report> getReportById(
            @PathVariable UUID id
    ) {
        return reportRepository.findById(id);
    }

    // CREATE
    @PostMapping
    public Report createReport(
            @RequestBody Report report
    ) {
        return reportRepository.save(report);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Report updateReport(
            @PathVariable UUID id,
            @RequestBody Report updatedReport
    ) {

        return reportRepository.findById(id)
                .map(report -> {

                    report.setName(updatedReport.getName());
                    report.setType(updatedReport.getType());
                    report.setFormat(updatedReport.getFormat());
                    report.setDescription(updatedReport.getDescription());

                    return reportRepository.save(report);

                }).orElseThrow(() ->
                        new RuntimeException(
                                "Report not found with id " + id
                        )
                );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteReport(
            @PathVariable UUID id
    ) {

        reportRepository.deleteById(id);

        return "Report deleted successfully";
    }
}