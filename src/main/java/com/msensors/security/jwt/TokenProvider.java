/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.security.jwt;

/**
 * Security token provider.
 * @since 0.0.0
 */
public interface TokenProvider {

    /**
     * Token from username.
     * @param uname Username
     * @return Token
     */
    String token(String uname);

    /**
     * Username by token.
     * @param token Token
     * @return User
     */
    String username(String token);

    /**
     * Is valid?
     * @param token Token
     * @param uname Username
     * @return Valid or not
     */
    boolean valid(String token, String uname);
}
