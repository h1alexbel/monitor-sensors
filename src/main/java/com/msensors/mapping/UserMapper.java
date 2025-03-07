/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.mapping;

import com.msensors.entity.RoleName;
import com.msensors.entity.User;
import com.msensors.entity.UserRole;
import com.msensors.rest.request.UserCreateDto;
import com.msensors.rest.request.UserReadDto;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
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
    @Mapping(target = "roles", ignore = true)
    User createToEntity(UserCreateDto create);

    /**
     * User entity to its read DTO.
     * @param user User
     * @return User read DTO
     */
    @Mapping(target = "roles", source = "roles")
    UserReadDto entityToRead(User user);

    /**
     * Map roles.
     * @param roles Roles
     * @return Mapped roles
     */
    default Set<RoleName> mapRoles(final Set<UserRole> roles) {
        final Set<RoleName> result;
        if (roles == null) {
            result = new HashSet<>(0);
        } else {
            result = roles.stream()
                .map(userRole -> RoleName.valueOf(String.valueOf(userRole.getName())))
                .collect(Collectors.toSet());
        }
        return result;
    }
}
