/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service;

import com.msensors.rest.request.UserCreateDto;
import com.msensors.rest.request.UserReadDto;

/**
 * Users.
 * @since 0.0.0
 */
public interface Users {

    /**
     * Save new user.
     * @param user User to save
     * @return User
     */
    UserReadDto save(UserCreateDto user);

    /**
     * User.
     * @param identifier ID
     * @return User
     */
    UserReadDto user(Long identifier);
}
