/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.repo;

import com.msensors.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repo.
 * @since 0.0.0
 */
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Find by username.
     * @param uname Username
     * @return User
     */
    Optional<User> findByUsername(String uname);
}
