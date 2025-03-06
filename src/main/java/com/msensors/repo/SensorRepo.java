/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.repo;

import com.msensors.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Sensor repository.
 * @since 0.0.0
 */
public interface SensorRepo extends JpaRepository<Sensor, Long> {
}
