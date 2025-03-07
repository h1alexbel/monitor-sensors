/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.mapping;

import com.msensors.entity.User;
import com.msensors.rest.request.UserCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * User mapper.
 * @since 0.0.0
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    /**
     * Create request to User entity.
     * @param create User DTO
     * @return User entity
     */
    @Mapping(target = "id", ignore = true)
    User createToEntity(UserCreateDto create);
}
