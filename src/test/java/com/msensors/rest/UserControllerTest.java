package com.msensors.rest;

import com.msensors.entity.RoleName;
import com.msensors.rest.request.UserCreateDto;
import com.msensors.security.SecurityConfig;
import com.msensors.security.jwt.TokenProvider;
import com.msensors.service.Users;
import org.cactoos.set.SetOf;
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
 * Tests for {@link UserController}.
 *
 * @since 0.0.0
 */
@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
@ExtendWith(MockitoExtension.class)
final class UserControllerTest {

    /**
     * Mock MVC.
     */
    @Autowired
    private MockMvc mvc;

    @Autowired
    @MockBean
    private Users users;

    @Autowired
    @MockBean
    private TokenProvider tokens;

    @Autowired
    @MockBean
    private UserDetailsService service;

    @Test
    @WithMockUser(username = "jeff", roles = "ADMIN")
    void allowsAdminAccess() throws Exception {
        this.mvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType("application/json")
                .content(
                    new ObjectMapper().writeValueAsString(
                        UserCreateDto.builder()
                            .username("joe")
                            .password("boom")
                            .roles(new SetOf<>(RoleName.VIEWER))
                            .build()
                    )
                )
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "jeff", roles = "VIEWER")
    void forbidsViewerAccess() throws Exception {
        this.mvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType("application/json")
                .content(
                    new ObjectMapper().writeValueAsString(
                        UserCreateDto.builder()
                            .username("test")
                            .password("boom")
                            .roles(new SetOf<>(RoleName.VIEWER))
                            .build()
                    )
                )
        ).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void forbidsAnonymous() throws Exception {
        this.mvc.perform(
            MockMvcRequestBuilders.post("/users")
                .contentType("application/json")
                .content(
                    new ObjectMapper().writeValueAsString(
                        UserCreateDto.builder()
                            .username("jeff")
                            .password("pass")
                            .roles(new SetOf<>(RoleName.VIEWER))
                            .build()
                    )
                )
        ).andExpect(MockMvcResultMatchers.status().isForbidden());
    }
}
