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
    void save(UserCreateDto user);

    /**
     * User.
     * @param username Username
     * @return User
     */
    UserReadDto user(String username);
}
