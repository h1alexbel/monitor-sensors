/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest.response;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.cactoos.Scalar;
import org.springframework.http.ResponseEntity;

/**
 * Response with errors.
 * @since 0.0.0
 */
@RequiredArgsConstructor
public final class ResponseWithErrors implements Scalar<ResponseEntity<?>> {

    /**
     * Errors.
     */
    private final Scalar<List<String>> errors;

    @SneakyThrows
    @Override
    public ResponseEntity<?> value() {
        return ResponseEntity.badRequest().body(this.errors.value());
    }
}
