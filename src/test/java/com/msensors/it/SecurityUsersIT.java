/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.it;

import com.msensors.entity.RoleName;
import com.msensors.fixtures.PostgresFixture;
import com.msensors.rest.request.UserCreateDto;
import com.msensors.service.Users;
import org.cactoos.set.SetOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Integration test for {@link com.msensors.service.SecurityUsers}.
 *
 * @since 0.0.0
 */
@SpringBootTest
final class SecurityUsersIT extends PostgresFixture {

    /**
     * Users.
     */
    @Autowired
    private Users users;

    /**
     * User details service.
     */
    @Autowired
    private UserDetailsService service;

    @Test
    void findsUser() {
        final String expected = "test";
        this.users.save(
            UserCreateDto.builder()
                .username(expected)
                .password("12345")
                .roles(new SetOf<>(RoleName.ADMIN))
                .build()
        );
        final String found = this.service.loadUserByUsername(expected).getUsername();
        MatcherAssert.assertThat(
            String.format(
                "User name: '%s' does not match with expected: '%s'",
                found, expected
            ),
            found,
            Matchers.equalTo(expected)
        );
    }
}
