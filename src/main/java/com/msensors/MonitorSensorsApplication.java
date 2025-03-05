/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Monitor Sensors Application Entry Point.
 * @since 0.0.0
 * @checkstyle HideUtilityClassConstructorCheck (3 lines)
 */
@SpringBootApplication
@SuppressWarnings("PMD.UseUtilityClass")
public class MonitorSensorsApplication {

    /**
     * Application entry point.
     * @param args Application arguments
     */
    public static void main(final String... args) {
        SpringApplication.run(MonitorSensorsApplication.class, args);
    }
}
