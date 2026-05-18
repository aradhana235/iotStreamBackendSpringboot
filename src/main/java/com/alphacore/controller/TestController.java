package com.alphacore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.alphacore.model.DashboardStats;
import com.alphacore.repository.DashboardRepository;
import com.alphacore.repository.DeviceRepository;
import com.alphacore.repository.AlarmRepository;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DashboardRepository dashboardRepository;

    // Total Devices Count API
    @GetMapping("/total-devices")
    public Long getTotalDevices() {

        return deviceRepository.getTotalDevices();
    }
    
    
    @Autowired
    private AlarmRepository alarmRepository;
    
    @GetMapping("/medium-count")
    public Long getMediumCount() {

        return alarmRepository.getMediumCount();
    }

    @GetMapping("/block-count")
    public Long getBlockCount() {

        return alarmRepository.getBlockCount();
    }

    @GetMapping("/full-PartialLeak-count")
    public Long getFullBlockCount() {

        return alarmRepository.getFullBlockCount();
    }

    @GetMapping("/full-leak-count")
    public Long getFullLeakCount() {

        return alarmRepository.getFullLeakCount();
    }

    // Dashboard Stats API
    @GetMapping("/dashboard-stats")
    public List<DashboardStats> getDashboardStats() {

        return dashboardRepository.findAll();
    }
}