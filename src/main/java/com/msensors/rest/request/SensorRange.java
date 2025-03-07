/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Sensor range.
 * @since 0.0.0
 * @checkstyle ConstantUsageCheck (30 lines)
 * @checkstyle DesignForExtensionCheck (20 lines)
 */
@Data
@AllArgsConstructor
public class SensorRange {

    /**
     * Range from.
     */
    @Min((long) Integer.MIN_VALUE)
    private final Integer from;

    /**
     * Range to.
     * @checkstyle MemberNameCheck (3 lines)
     */
    @Max((long) Integer.MAX_VALUE)
    private final Integer to;

    @AssertTrue(message = "Range 'from' must be less than 'to'")
    public boolean isValidRange() {
        return this.from == null || this.to == null || this.from < this.to;
    }
}
