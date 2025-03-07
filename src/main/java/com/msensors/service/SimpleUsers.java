/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service;

import com.msensors.mapping.UserMapper;
import com.msensors.repo.UserRepo;
import com.msensors.rest.request.UserCreateDto;
import com.msensors.rest.request.UserReadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Simple users.
 * @since 0.0.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SimpleUsers implements Users {

    /**
     * User repo.
     */
    private final UserRepo repo;

    /**
     * User mapping.
     */
    private final UserMapper mapping;

    @Transactional
    @Override
    public void save(final UserCreateDto user) {
        this.repo.save(this.mapping.createToEntity(user));
    }

    @Override
    public UserReadDto user(final String username) {
        throw new UnsupportedOperationException("#user()");
    }
}
