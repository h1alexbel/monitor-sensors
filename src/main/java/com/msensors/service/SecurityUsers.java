/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service;

import com.msensors.entity.User;
import com.msensors.repo.UserRepo;
import java.util.Collection;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Security users.
 *
 * @since 0.0.0
 * @checkstyle DesignForExtensionCheck (50 lines)
 */
@Service
@RequiredArgsConstructor
public class SecurityUsers implements UserDetailsService {

    /**
     * User repo.
     */
    private final UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User found = this.repo.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException(
                String.format(
                    "User with username %s not found",
                    username
                )
            )
        );
        return new org.springframework.security.core.userdetails.User(
            found.getUsername(),
            found.getPassword(),
            SecurityUsers.authorities(found)
        );
    }

    private static Collection<GrantedAuthority> authorities(final User user) {
        final Collection<GrantedAuthority> authorities = new HashSet<>(0);
        user.getRoles().forEach(
            r -> authorities.add(
                new SimpleGrantedAuthority(
                    String.format(
                        "ROLE_%s",
                        r.getName().name()
                    )
                )
            )
        );
        return authorities;
    }
}
