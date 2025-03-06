/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Sensor read DTO.
 * @since 0.0.0
 * @checkstyle ConstantUsageCheck (50 lines)
 */
@Data
@AllArgsConstructor
public class SensorReadDto {

    /**
     * ID.
     */
    private Long id;

    /**
     * Name.
     */
    private final String name;

    /**
     * Model.
     */
    private final String model;

    /**
     * Range.
     */
    private final SensorRange range;

    /**
     * Type.
     */
    private final String type;

    /**
     * Unit.
     */
    private final String unit;

    /**
     * Location.
     */
    private final String location;

    /**
     * Description.
     */
    private final String description;
}
