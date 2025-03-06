/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.it;

import com.msensors.fixtures.PostgresFixture;
import com.msensors.rest.request.SensorCreateDto;
import com.msensors.rest.request.SensorRange;
import com.msensors.service.Sensors;
import com.yegor256.MayBeSlow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration test for {@link com.msensors.service.SimpleSensors}.
 *
 * @since 0.0.0
 */
@SpringBootTest
@SuppressWarnings("JTCOP.RuleInheritanceInTests")
final class SimpleSensorsIT extends PostgresFixture {

    /**
     * Sensors.
     */
    @Autowired
    private Sensors sensors;

    @ExtendWith(MayBeSlow.class)
    @Test
    @SuppressWarnings("JTCOP.RuleAssertionMessage")
    void createsSensorWithRequiredFields() {
        Assertions.assertDoesNotThrow(
            () -> this.sensors.save(
                SensorCreateDto.builder()
                    .name("foo")
                    .model("FF")
                    .type("Boom")
                    .range(
                        new SensorRange(1, 2)
                    )
                    .unit("C")
                    .build()
            ),
            () -> "Exception was thrown, but it should not"
        );
    }
}
