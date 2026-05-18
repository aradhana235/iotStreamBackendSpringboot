package com.alphacore.repository;

import com.alphacore.model.DashboardStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardRepository extends JpaRepository<DashboardStats, Long> {
}