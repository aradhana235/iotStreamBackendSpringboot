package com.alphacore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dashboard_stats")
public class DashboardStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active_streams")
    private Integer activeStreams;

    @Column(name = "threat_alerts")
    private Integer threatAlerts;

    @Column(name = "avg_temperture")
    private Double avgTemperture;

    @Column(name = "ai_load")
    private Double aiLoad;

    public DashboardStats() {
    }

    public Long getId() {
        return id;
    }

    public Integer getActiveStreams() {
        return activeStreams;
    }

    public void setActiveStreams(Integer activeStreams) {
        this.activeStreams = activeStreams;
    }

    public Integer getThreatAlerts() {
        return threatAlerts;
    }

    public void setThreatAlerts(Integer threatAlerts) {
        this.threatAlerts = threatAlerts;
    }

    public Double getAvgTemperture() {
        return avgTemperture;
    }

    public void setAvgTemperture(Double avgTemperture) {
        this.avgTemperture = avgTemperture;
    }

    public Double getAiLoad() {
        return aiLoad;
    }

    public void setAiLoad(Double aiLoad) {
        this.aiLoad = aiLoad;
    }
}