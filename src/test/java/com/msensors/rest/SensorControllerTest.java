/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest;

import com.msensors.security.SecurityConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

/**
 * Tests for {@link SensorController}.
 *
 * @since 0.0.0
 */
@WebMvcTest(SensorController.class)
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
@ExtendWith(MockitoExtension.class)
final class SensorControllerTest {


}
