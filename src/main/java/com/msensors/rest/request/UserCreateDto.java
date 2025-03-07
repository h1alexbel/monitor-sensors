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
 * User create DTO.
 * @since 0.0.0
 */
@Data
@AllArgsConstructor
@Builder
public class UserCreateDto {

    /**
     * Username.
     */
    private final String username;

    /**
     * Password.
     */
    private final String password;

    /**
     * Roles.
     */
    private final Set<RoleName> roles;
}
