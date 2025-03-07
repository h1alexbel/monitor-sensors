/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest;

import com.msensors.rest.request.SensorCreateDto;
import com.msensors.rest.request.SensorReadDto;
import com.msensors.rest.request.SensorUpdateDto;
import com.msensors.rest.response.ResponseValidated;
import com.msensors.rest.response.ResponseWithErrors;
import com.msensors.rest.response.ValidationErrors;
import com.msensors.service.Sensors;
import jakarta.validation.Valid;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sensor controller.
 * @since 0.0.0
 * @checkstyle DesignForExtensionCheck (100 lines)
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
     * @param validation Validation result
     * @return Sensor
     * @throws Exception if something went wrong
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> create(
        @Valid @RequestBody final SensorCreateDto create,
        final BindingResult validation
    ) throws Exception {
        return new ResponseValidated(
            validation,
            new ResponseWithErrors(new ValidationErrors(validation)),
            () -> ResponseEntity.ok(this.sensors.save(create))
        ).value();
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
     * Delete sensor.
     * @param identifier Identifier
     */
    @DeleteMapping("/{identifier}")
    public void delete(@PathVariable final Long identifier) {
        this.sensors.delete(identifier);
    }

    /**
     * Update sensor.
     * @param identifier Identifier
     * @param request Update request
     * @param validation Validation result
     * @return Updated sensor
     * @throws Exception if something went wrong
     */
    @PutMapping("/{identifier}")
    public ResponseEntity<?> update(
        @PathVariable final Long identifier, @Valid @RequestBody final SensorUpdateDto request,
        final BindingResult validation
    ) throws Exception {
        return new ResponseValidated(
            validation,
            new ResponseWithErrors(new ValidationErrors(validation)),
            () -> ResponseEntity.ok(this.sensors.update(identifier, request))
        ).value();
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
}
