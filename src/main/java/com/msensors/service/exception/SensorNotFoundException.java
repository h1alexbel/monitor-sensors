/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Sensor not found.
 * @since 0.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class SensorNotFoundException extends RuntimeException {

    /**
     * Ctor.
     * @param message Message
     */
    public SensorNotFoundException(final String message) {
        super(message);
    }

    /**
     * Ctor.
     * @param message Message
     * @param cause Cause
     */
    public SensorNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
