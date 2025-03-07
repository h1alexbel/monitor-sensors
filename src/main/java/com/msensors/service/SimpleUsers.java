/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service;

import com.msensors.entity.User;
import com.msensors.entity.UserRole;
import com.msensors.mapping.UserMapper;
import com.msensors.repo.UserRepo;
import com.msensors.rest.request.UserCreateDto;
import com.msensors.rest.request.UserReadDto;
import com.msensors.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Simple users.
 * @since 0.0.0
 * @checkstyle DesignForExtensionCheck (55 lines)
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

    /**
     * Password encoder.
     */
    private final PasswordEncoder encoder;

    @Transactional
    @Override
    public UserReadDto save(final UserCreateDto user) {
        final User entity = this.mapping.createToEntity(user);
        user.getRoles().forEach(
            r -> {
                final UserRole role = new UserRole();
                role.setName(r);
                role.setUser(entity);
                entity.getRoles().add(role);
            }
        );
        entity.setPassword(this.encoder.encode(entity.getPassword()));
        return this.mapping.entityToRead(this.repo.save(entity));
    }

    @Override
    public UserReadDto user(final Long identifier) {
        return this.repo.findById(identifier).map(this.mapping::entityToRead)
            .orElseThrow(
                () -> new UserNotFoundException(
                    String.format(
                        "User with ID %d not found",
                        identifier
                    )
                )
            );
    }
}
