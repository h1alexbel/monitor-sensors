/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest;

import com.msensors.rest.request.SensorCreateDto;
import com.msensors.rest.request.SensorReadDto;
import com.msensors.service.Sensors;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sensor controller.
 * @since 0.0.0
 */
@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {

    /**
     * Sensors.
     */
    private final Sensors sensors;

    /**
     * Create sensor.
     * @param create Sensor to create
     * @return Sensor
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SensorReadDto create(@RequestBody final SensorCreateDto create) {
        return this.sensors.save(create);
    }

    /**
     * Read sensor by its ID.
     * @param identifier ID
     * @return Sensor
     */
    @GetMapping("/{identifier}")
    public SensorReadDto readById(@PathVariable final Long identifier) {
        return this.sensors.sensor(identifier);
    }

    @GetMapping
    public Collection<SensorReadDto> all() {
        return this.sensors.all();
    }

    /**
     * Search sensors containing input value in the name or model.
     * @param input Input value
     * @return Sensors
     */
    @GetMapping("/search")
    public Collection<SensorReadDto> search(@RequestParam final String input) {
        return this.sensors.search(input);
    }

    /**
     * Delete sensor.
     * @param identifier Identifier
     */
    @DeleteMapping("/{identifier}")
    public void delete(@PathVariable final Long identifier) {
        this.sensors.delete(identifier);
    }
}
