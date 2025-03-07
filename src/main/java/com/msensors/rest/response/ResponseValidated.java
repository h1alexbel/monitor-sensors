/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest.response;

import lombok.RequiredArgsConstructor;
import org.cactoos.Scalar;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/**
 * Response, potentially with validation errors inside.
 * @since 0.0.0
 */
@RequiredArgsConstructor
public final class ResponseValidated implements Scalar<ResponseEntity<?>> {

    /**
     * Validation result.
     */
    private final BindingResult validation;

    /**
     * Error response.
     */
    private final Scalar<ResponseEntity<?>> err;

    /**
     * Success response.
     */
    private final Scalar<ResponseEntity<?>> success;

    @Override
    public ResponseEntity<?> value() throws Exception {
        final ResponseEntity<?> result;
        if (this.validation.hasErrors()) {
            result = this.err.value();
        } else {
            result = this.success.value();
        }
        return result;
    }
}
