/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.fixtures;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

/**
 * PostgreSQL fixture.
 * @since 0.0.0
 * @checkstyle HideUtilityClassConstructorCheck (2 line)
 */
@SuppressWarnings("PMD.UseUtilityClass")
@DirtiesContext
public class PostgresFixture {

    /**
     * PostgreSQL container.
     */
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>(
        "postgres:16"
    ).withDatabaseName("postgres").withUsername("postgres").withPassword("postgres")
        .waitingFor(Wait.forListeningPort());

    @BeforeAll
    static void setUp() {
        PostgresFixture.POSTGRES.start();
        System.setProperty("spring.datasource.url", POSTGRES.getJdbcUrl());
        System.setProperty("spring.datasource.username", POSTGRES.getUsername());
        System.setProperty("spring.datasource.password", POSTGRES.getPassword());
    }

    @AfterAll
    static void afterAll() {
        PostgresFixture.POSTGRES.stop();
    }
}
