/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.it;

import com.msensors.entity.RoleName;
import com.msensors.fixtures.PostgresFixture;
import com.msensors.rest.request.UserCreateDto;
import com.msensors.service.Users;
import com.yegor256.MayBeSlow;
import org.cactoos.set.SetOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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
                    .roles(new SetOf<>(RoleName.VIEWER))
                    .build()
            ),
            () -> "Exception was thrown, but it should not"
        );
    }

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
        final String found = this.users.user(expected).getUsername();
        MatcherAssert.assertThat(
            String.format(
                "User name: '%s' does not match with expected: '%s'",
                found, expected
            ),
            found,
            Matchers.equalTo(expected)
        );
    }

    @Test
    void savesUserWithMultipleRoles() {
        final String username = "foo";
        this.users.save(
            UserCreateDto.builder()
                .username(username)
                .password("bar")
                .roles(new SetOf<>(RoleName.VIEWER, RoleName.ADMIN))
                .build()
        );
        MatcherAssert.assertThat(
            "User should have both roles, but its not",
            this.users.user(username).getRoles(),
            Matchers.iterableWithSize(2)
        );
    }
}
