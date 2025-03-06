/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service;

import com.msensors.rest.request.SensorCreateDto;
import com.msensors.rest.request.SensorReadDto;

/**
 * Sensors.
 * @since 0.0.0
 */
public interface Sensors {

    /**
     * Save sensor.
     * @param sensor Sensor to be saved
     */
    void save(SensorCreateDto sensor);

    /**
     * Find sensor by its ID.
     * @param identifier Identifier
     * @return Sensor
     */
    SensorReadDto sensor(Long identifier);
}
