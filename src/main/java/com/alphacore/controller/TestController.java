package com.alphacore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alphacore.model.Alarm;
import com.alphacore.model.DashboardStats;
import com.alphacore.repository.AlarmRepository;
import com.alphacore.repository.DashboardRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private AlarmRepository alarmRepository;

    // =========================
    // COUNTS
    // =========================

    @GetMapping("/medium-count")
    public Long getMediumCount() {

        return alarmRepository.getMediumCount();
    }

    @GetMapping("/block-count")
    public Long getBlockCount() {

        return alarmRepository.getBlockCount();
    }

    @GetMapping("/partial-leak-count")
    public Long getPartialLeakCount() {

        return alarmRepository.getPartialLeakCount();
    }

    @GetMapping("/full-leak-count")
    public Long getFullLeakCount() {

        return alarmRepository.getFullLeakCount();
    }

    // =========================
    // ALL ALERTS TABLE DATA
    // =========================

    @GetMapping("/alarms")
    public List<Alarm> getAllAlarms() {

        return alarmRepository.findAll();
    }

    // =========================
    // DASHBOARD
    // =========================

    @GetMapping("/dashboard-stats")
    public List<DashboardStats> getDashboardStats() {

        return dashboardRepository.findAll();
    }
}