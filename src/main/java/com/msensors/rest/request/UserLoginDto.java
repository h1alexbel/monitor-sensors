package com.msensors.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * User login DTO.
 * @since 0.0.0
 */
@Data
@AllArgsConstructor
@Builder
public class UserLoginDto {

    /**
     * Username.
     */
    private final String username;

    /**
     * Password.
     */
    private final String password;
}
