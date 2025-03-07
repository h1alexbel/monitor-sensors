package com.msensors.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration.
 *
 * @since 0.0.0
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    /**
     * Bcrypt strength.
     */
    private static final int BCRYPT_STRENGTH = 12;

    /**
     * JWT filter.
     */
    private final JwtFilter filter;

    /**
     * User details service.
     */
    private final UserDetailsService users;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                rqs -> {
                    rqs.requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/sensors/**").hasAnyRole("VIEWER", "ADMIN")
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated();
                }
            )
            .authenticationProvider(this.provider())
            .addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider provider() {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider(this.encoder());
        provider.setUserDetailsService(this.users);
        return provider;
    }

    @Bean
    public AuthenticationManager manager(final AuthenticationConfiguration conf)
        throws Exception {
        return conf.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(SecurityConfig.BCRYPT_STRENGTH);
    }
}
