/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service.exception;

/**
 * User not found exception.
 * @since 0.0.0
 */
public final class UserNotFoundException extends RuntimeException {

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
