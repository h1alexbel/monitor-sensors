package com.msensors.service;

import com.msensors.rest.request.UserCreateDto;
import com.msensors.rest.request.UserReadDto;

/**
 * Users.
 * @since 0.0.0
 */
public interface Users {

    /**
     * Save new user.
     * @param user User to save
     */
    UserReadDto save(UserCreateDto user);

    /**
     * User.
     * @param identifier ID
     * @return User
     */
    UserReadDto user(Long identifier);
}
