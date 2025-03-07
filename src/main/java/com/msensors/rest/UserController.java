/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest;

import com.msensors.rest.request.UserCreateDto;
import com.msensors.rest.request.UserReadDto;
import com.msensors.service.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 * @since 0.0.0
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    /**
     * Users.
     */
    private final Users users;

    /**
     * Register new user.
     * @param create User create DTO
     * @return User read DTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserReadDto register(@RequestBody final UserCreateDto create) {
        return this.users.save(create);
    }
}
