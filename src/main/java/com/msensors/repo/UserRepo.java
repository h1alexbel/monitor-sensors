/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.repo;

import com.msensors.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User repo.
 * @since 0.0.0
 */
public interface UserRepo extends JpaRepository<User, Long> {
}
