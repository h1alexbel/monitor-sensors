/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JWT token.
 * @since 0.0.0
 * @checkstyle DesignForExtensionCheck (50 lines)
 */
@Component
@RequiredArgsConstructor
public class JwtProvider implements TokenProvider {

    /**
     * Secret.
     */
    @Value("${jwt.secret-key}")
    private String secret;

    @Override
    public String token(final String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86_400_000L))
            .signWith(SignatureAlgorithm.HS512, this.secret)
            .compact();
    }

    @Override
    public String username(final String token) {
        return Jwts.parser()
            .setSigningKey(this.secret)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    @Override
    public boolean valid(final String token, final String username) {
        final String extracted = Jwts.parser()
            .setSigningKey(this.secret)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
        final Date expiration = Jwts.parser()
            .setSigningKey(this.secret)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();
        return extracted.equals(username) && !expiration.before(new Date());
    }
}
