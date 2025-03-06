/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service;

import com.msensors.rest.request.SensorCreateDto;
import com.msensors.rest.request.SensorReadDto;
import java.util.Collection;

/**
 * Sensors.
 * @since 0.0.0
 */
public interface Sensors {

    /**
     * Save sensor.
     * @param sensor Sensor to be saved
     * @return Sensor
     */
    SensorReadDto save(SensorCreateDto sensor);

    /**
     * Find sensor by its ID.
     * @param identifier Identifier
     * @return Sensor
     */
    SensorReadDto sensor(Long identifier);

    /**
     * All sensors.
     * @return All sensors
     */
    Collection<SensorReadDto> all();

    /**
     * Delete.
     * @param identifier Identifier
     */
    void delete(Long identifier);

    /**
     * Sensors by input.
     * @param input Input
     * @return All sensors containing input value
     */
    Collection<SensorReadDto> search(String input);
}
