package com.alphacore.controller;

import com.alphacore.model.Device;
import com.alphacore.repository.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
@CrossOrigin(origins = "http://localhost:5173")
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;

    // =====================================
    // TOTAL DEVICE COUNT
    // =====================================

    @GetMapping("/total-devices")
    public Long getTotalDevices() {

        return deviceRepository.count();
    }

    // =====================================
    // GET ALL DEVICES
    // =====================================

    @GetMapping
    public List<Device> getAllDevices() {

        return deviceRepository.findAll();
    }

    // =====================================
    // GET DEVICE BY ID
    // =====================================

    @GetMapping("/{id}")
    public Optional<Device> getDeviceById(
            @PathVariable UUID id
    ) {

        return deviceRepository.findById(id);
    }

    // =====================================
    // CREATE DEVICE
    // =====================================

    @PostMapping
    public Device createDevice(
            @RequestBody Device device
    ) {

        // AUTO GENERATE NEW ID
        device.setId(null);

        // AUTO DEFAULT VALUES
        device.setTenantId(
                UUID.fromString(
                        "2e59b6d0-b337-11f0-911b-f16e30c8bd3b"
                )
        );

        device.setDeviceProfileId(
                UUID.fromString(
                        "2eb3bdb0-b337-11f0-911b-f16e30c8bd3b"
                )
        );

        return deviceRepository.save(device);
    }

    // =====================================
    // UPDATE DEVICE
    // =====================================

    @PutMapping("/{id}")
    public Device updateDevice(
            @PathVariable UUID id,
            @RequestBody Device updatedDevice
    ) {

        return deviceRepository.findById(id)
                .map(device -> {

                    // BASIC FIELDS
                    device.setName(
                            updatedDevice.getName()
                    );

                    device.setType(
                            updatedDevice.getType()
                    );

                    device.setLabel(
                            updatedDevice.getLabel()
                    );

                    // CUSTOMER
                    device.setCustomerId(
                            updatedDevice.getCustomerId()
                    );

                    // OPTIONAL UPDATE
                    if (updatedDevice.getTenantId() != null) {

                        device.setTenantId(
                                updatedDevice.getTenantId()
                        );
                    }

                    if (updatedDevice.getDeviceProfileId() != null) {

                        device.setDeviceProfileId(
                                updatedDevice.getDeviceProfileId()
                        );
                    }

                    return deviceRepository.save(device);

                }).orElseThrow(() ->
                        new RuntimeException(
                                "Device not found with id " + id
                        )
                );
    }

    // =====================================
    // DELETE DEVICE
    // =====================================

    @DeleteMapping("/{id}")
    public String deleteDevice(
            @PathVariable UUID id
    ) {

        deviceRepository.deleteById(id);

        return "Device deleted successfully";
    }
}