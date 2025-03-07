package com.msensors.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * User create DTO.
 * @since 0.0.0
 */
@Data
@AllArgsConstructor
@Builder
public class UserCreateDto {

    /**
     * Username.
     */
    private final String username;

    /**
     * Password.
     */
    private final String password;
}
