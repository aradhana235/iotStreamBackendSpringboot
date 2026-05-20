package com.alphacore.repository;

import com.alphacore.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {

    // OPTIONAL: custom count (not required because JpaRepository already has count())
    long count();
}



