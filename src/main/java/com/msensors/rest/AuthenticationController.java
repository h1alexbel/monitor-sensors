package com.msensors.rest;

import com.msensors.rest.request.UserLoginDto;
import com.msensors.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication controller.
 * @since 0.0.0
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * Tokens.
     */
    private final TokenProvider tokens;

    /**
     * Authentication manager.
     */
    private final AuthenticationManager auth;

    @PostMapping("/login")
    public String login(@RequestBody final UserLoginDto login) {
        this.auth.authenticate(
            new UsernamePasswordAuthenticationToken(
                login.getUsername(),
                login.getPassword()
            )
        );
        return this.tokens.token(login.getUsername());
    }
}
