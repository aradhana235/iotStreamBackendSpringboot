package com.alphacore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alphacore.model.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("SELECT COUNT(d) FROM Device d")
    Long getTotalDevices();
}