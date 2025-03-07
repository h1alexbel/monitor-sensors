/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest.request;

import com.msensors.entity.SensorType;
import com.msensors.entity.SensorUnit;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Sensor create DTO.
 *
 * @since 0.0.0
 * @checkstyle ConstantUsageCheck (50 lines)
 */
@Data
@AllArgsConstructor
@Builder
public class SensorCreateDto {

    /**
     * Name.
     */
    @NotNull
    @Size(min = 3, max = 30)
    private final String name;

    /**
     * Model.
     */
    @NotNull
    @Size(max = 15)
    private final String model;

    /**
     * Range.
     */
    @Valid
    private final SensorRange range;

    /**
     * Type.
     */
    private final SensorType type;

    /**
     * Unit.
     */
    private final SensorUnit unit;

    /**
     * Location.
     */
    @Size(max = 40)
    private final String location;

    /**
     * Description.
     */
    @Size(max = 200)
    private final String description;
}
