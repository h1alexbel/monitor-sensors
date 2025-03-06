/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.repo;

import com.msensors.entity.Sensor;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Sensor repository.
 * @since 0.0.0
 */
public interface SensorRepo extends JpaRepository<Sensor, Long> {

    /**
     * Find all by input.
     * @param name Name
     * @param model Model
     * @return All sensors with name or model containing input value
     */
    Collection<Sensor> findByNameContainingIgnoreCaseOrModelContainingIgnoreCase(String name, String model);
}
