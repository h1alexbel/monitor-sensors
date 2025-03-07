/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.cactoos.Scalar;
import org.springframework.validation.BindingResult;

/**
 * Validation errors.
 *
 * @since 0.0.0
 */
@RequiredArgsConstructor
public final class ValidationErrors implements Scalar<List<String>> {

    /**
     * Validation result.
     */
    private final BindingResult result;

    @Override
    public List<String> value() {
        return this.result.getFieldErrors()
            .stream()
            .map(err -> String.format("%s:%s", err.getField(), err.getDefaultMessage()))
            .collect(Collectors.toList());
    }
}
