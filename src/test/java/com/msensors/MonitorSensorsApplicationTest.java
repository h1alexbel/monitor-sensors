/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors;

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
final class MonitorSensorsApplicationTest {

    /**
     * Application context.
     */
    @Autowired
    private ApplicationContext context;

    @ExtendWith(MayBeSlow.class)
    @Test
    void runs() {
        MatcherAssert.assertThat(
            "Context should not be NULL",
            this.context,
            Matchers.notNullValue()
        );
    }
}
