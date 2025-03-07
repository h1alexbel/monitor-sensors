/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User not found exception.
 * @since 0.0.0
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Ctor.
     * @param message Message
     */
    public UserNotFoundException(final String message) {
        super(message);
    }

    /**
     * Ctor.
     * @param message Message
     * @param cause Cause
     */
    public UserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
