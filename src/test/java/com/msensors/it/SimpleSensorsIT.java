/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.it;

import com.msensors.fixtures.PostgresFixture;
import com.msensors.rest.request.SensorCreateDto;
import com.msensors.rest.request.SensorRange;
import com.msensors.rest.request.SensorReadDto;
import com.msensors.service.Sensors;
import com.msensors.service.exception.SensorNotFoundException;
import com.yegor256.MayBeSlow;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.llorllale.cactoos.matchers.Throws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration test for {@link com.msensors.service.SimpleSensors}.
 *
 * @since 0.0.0
 */
@ExtendWith(MayBeSlow.class)
@SpringBootTest
@SuppressWarnings("JTCOP.RuleInheritanceInTests")
final class SimpleSensorsIT extends PostgresFixture {

    /**
     * Sensors.
     */
    @Autowired
    private Sensors sensors;

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

    @Test
    @SuppressWarnings("JTCOP.RuleAssertionMessage")
    void deletesSensorById() {
        Assertions.assertDoesNotThrow(
            () -> this.sensors.delete(this.withSensor("foo", "bar").getId()),
            () -> "Exception was thrown, but it should not"
        );
    }

    @Test
    void throwsNotFoundWhenTryingToDeleteNonExisting() {
        MatcherAssert.assertThat(
            "Exception was not thrown, but it should",
            () -> {
                this.sensors.delete(999L);
                return 0;
            },
            new Throws<>(
                "Sensor with ID 999 was not found", SensorNotFoundException.class
            )
        );
    }

    private SensorReadDto withSensor(final String name, final String model) {
        return this.sensors.save(
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
