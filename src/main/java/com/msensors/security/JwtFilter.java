/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.security;

import com.msensors.security.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT authentication filter.
 *
 * @since 0.0.0
 * @checkstyle DesignForExtensionCheck (80 lines)
 * @checkstyle NestedIfDepthCheck (50 lines)
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    /**
     * Tokens.
     */
    private final TokenProvider tokens;

    /**
     * Users.
     */
    private final UserDetailsService users;

    @Override
    @SuppressWarnings({"PMD.CollapsibleIfStatements", "PMD.AvoidDeeplyNestedIfStmts"})
    protected void doFilterInternal(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain chain
    ) throws ServletException, IOException {
        final String token = JwtFilter.token(request);
        if (!token.isEmpty()) {
            final String username = this.tokens.username(token);
            if (
                username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null
            ) {
                if (this.tokens.valid(token, username)) {
                    final UserDetails details = this.users.loadUserByUsername(username);
                    SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                            details, "", details.getAuthorities()
                        )
                    );
                }
            }
        }
        chain.doFilter(request, response);
    }

    private static String token(final HttpServletRequest request) {
        final String result;
        final String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            result = bearer.substring(7);
        } else {
            result = "";
        }
        return result;
    }
}
