/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.it;

import com.msensors.MonitorSensorsApplication;
import com.msensors.fixtures.PostgresFixture;
import com.yegor256.MayBeSlow;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * Tests for {@link MonitorSensorsApplication}.
 * @since 0.0.0
 */
@SpringBootTest
@SuppressWarnings("JTCOP.RuleInheritanceInTests")
final class MonitorSensorsApplicationIT extends PostgresFixture {

    /**
     * Application context.
     */
    @Autowired
    private ApplicationContext context;

    @ExtendWith(MayBeSlow.class)
    @Test
    void runs() {
        MatcherAssert.assertThat(
            "Context appeared to be NULL, but it should not be",
            this.context,
            Matchers.notNullValue()
        );
    }
}
