/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.rest;

import com.msensors.entity.SensorType;
import com.msensors.entity.SensorUnit;
import com.msensors.rest.request.SensorCreateDto;
import com.msensors.rest.request.SensorRange;
import com.msensors.security.SecurityConfig;
import com.msensors.security.jwt.TokenProvider;
import com.msensors.service.Sensors;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests for {@link SensorController}.
 *
 * @since 0.0.0
 */
@WebMvcTest(SensorController.class)
@Import(SecurityConfig.class)
@ExtendWith(MockitoExtension.class)
@SuppressWarnings("JTCOP.RuleAssertionMessage")
final class SensorControllerTest {

    /**
     * Mock MVC.
     */
    @Autowired
    private MockMvc mvc;

    /**
     * Tokens.
     */
    @Autowired
    @MockBean
    private TokenProvider tokens;

    /**
     * Service.
     */
    @Autowired
    @MockBean
    private UserDetailsService service;

    /**
     * Sensors.
     */
    @Autowired
    @MockBean
    private Sensors sensors;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void allowsCreateAccessForAdmin() throws Exception {
        this.mvc.perform(
            MockMvcRequestBuilders.post("/sensors")
                .contentType("application/json")
                .content(
                    new ObjectMapper().writeValueAsString(
                        SensorCreateDto.builder()
                            .name("test")
                            .model("f12")
                            .type(SensorType.PRESSURE)
                            .range(new SensorRange(2, 3))
                            .unit(SensorUnit.BAR)
                            .build()
                    )
                )
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "simple_user", roles = "VIEWER")
    void forbidsAccessForViewer() throws Exception {
        this.mvc.perform(
            MockMvcRequestBuilders.post("/sensors")
                .contentType("application/json")
                .content(
                    new ObjectMapper().writeValueAsString(
                        SensorCreateDto.builder()
                            .name("test")
                            .model("f12")
                            .type(SensorType.TEMPERATURE)
                            .range(new SensorRange(3, 8))
                            .unit(SensorUnit.CELSIUS)
                            .build()
                    )
                )
        ).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "foo", roles = "VIEWER")
    void allowsReadAccessForViewer() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/sensors"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "both", roles = {"ADMIN", "VIEWER"})
    void allowsSearchForAdminAndViewerOnly() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/sensors/search?input=ff"))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void forbidsSearchForAdmin() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/sensors/search?input=ff"))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "view", roles = "VIEWER")
    void forbidsSearchForViewer() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("/sensors/search?input=ff"))
            .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "bar", roles = "ADMIN")
    void returnsValidationErrors() throws Exception {
        this.mvc.perform(
            MockMvcRequestBuilders.post("/sensors")
                .contentType("application/json")
                .content(
                    new ObjectMapper().writeValueAsString(
                        SensorCreateDto.builder()
                            .name("broken")
                            .model("b99")
                            .type(SensorType.PRESSURE)
                            .range(new SensorRange(5, 1))
                            .unit(SensorUnit.BAR)
                            .build()
                    )
                )
        ).andExpect(
            MockMvcResultMatchers.content().string(
                Matchers.containsString("range.validRange:Range 'from' must be less than 'to'")
            )
        );
    }
}
