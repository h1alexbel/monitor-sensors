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
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
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

    @ExtendWith(MayBeSlow.class)
    @Test
    void findsAllSensors() {
        this.sensors.save(
            SensorCreateDto.builder()
                .name("abc-sensor")
                .model("F11")
                .type("check")
                .range(
                    new SensorRange(3, 8)
                )
                .unit("C")
                .build()
        );
        MatcherAssert.assertThat(
            "Sensors are empty, but they should not",
            this.sensors.all(),
            Matchers.hasSize(Matchers.greaterThan(0))
        );
    }

    @ExtendWith(MayBeSlow.class)
    @Test
    void findsSensorsContainingName() {
        this.withSensor("test", "M82");
        this.withSensor("test-name", "M22");
        this.withSensor("foo", "M11");
        MatcherAssert.assertThat(
            "Found sensors does not match with expected",
            this.sensors.search("test"),
            Matchers.hasSize(2)
        );
    }

    @ExtendWith(MayBeSlow.class)
    @Test
    void findsSensorsContainingModel() {
        this.withSensor("foo", "M822002");
        this.withSensor("bar", "M8220012");
        this.withSensor("xyz", "M124240");
        MatcherAssert.assertThat(
            "Found sensors does not match with expected",
            this.sensors.search("M822"),
            Matchers.hasSize(2)
        );
    }

    private void withSensor(final String name, final String model) {
        this.sensors.save(
            SensorCreateDto.builder()
                .name(name)
                .model(model)
                .type("check")
                .range(
                    new SensorRange(3, 8)
                )
                .unit("C")
                .build()
        );
    }
}
