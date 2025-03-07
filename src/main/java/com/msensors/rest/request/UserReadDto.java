/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest.request;

import com.msensors.entity.RoleName;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * User read DTO.
 * @since 0.0.0
 * @checkstyle ConstantUsageCheck (50 lines)
 */
@Data
@AllArgsConstructor
@Builder
public class UserReadDto {

    /**
     * ID.
     */
    private final Long id;

    /**
     * Username.
     */
    private final String username;

    /**
     * Roles.
     */
    private final Set<RoleName> roles;
}
