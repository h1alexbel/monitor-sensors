/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * User login DTO.
 * @since 0.0.0
 * @checkstyle ConstantUsageCheck (20 line)
 */
@Data
@AllArgsConstructor
@Builder
public class UserLoginDto {

    /**
     * Username.
     */
    private final String username;

    /**
     * Password.
     */
    private final String password;
}
