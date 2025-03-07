/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.it;

import com.msensors.fixtures.PostgresFixture;
import com.msensors.rest.request.UserCreateDto;
import com.msensors.service.Users;
import com.yegor256.MayBeSlow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration test for {@link com.msensors.service.SimpleUsers}.
 * @since 0.0.0
 */
@ExtendWith(MayBeSlow.class)
@SpringBootTest
@SuppressWarnings("JTCOP.RuleInheritanceInTests")
final class SimpleUsersIT extends PostgresFixture {

    /**
     * Users.
     */
    @Autowired
    private Users users;

    @Test
    @SuppressWarnings("JTCOP.RuleAssertionMessage")
    void savesUser() {
        Assertions.assertDoesNotThrow(
            () -> this.users.save(
                UserCreateDto.builder()
                    .username("jeff")
                    .password("myPass")
                    .build()
            ),
            () -> "Exception was thrown, but it should not"
        );
    }
}
