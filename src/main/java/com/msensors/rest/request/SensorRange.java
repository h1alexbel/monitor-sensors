/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Sensor range.
 * @since 0.0.0
 * @checkstyle ConstantUsageCheck (30 lines)
 */
@Data
@AllArgsConstructor
public class SensorRange {

    /**
     * Range from.
     */
    private final Integer from;

    /**
     * Range to.
     * @checkstyle MemberNameCheck (3 lines)
     */
    private final Integer to;
}
