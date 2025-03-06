/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.mapping;

import com.msensors.entity.Sensor;
import com.msensors.rest.request.SensorCreateDto;
import com.msensors.rest.request.SensorReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Sensor mapper.
 * @since 0.0.0
 */
@Mapper(componentModel = "spring")
public interface SensorMapper {

    /**
     * Create request to Sensor entity.
     * @param create Create DTO
     * @return Sensor entity
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rangeFrom", source = "range.from")
    @Mapping(target = "rangeTo", source = "range.to")
    Sensor createToEntity(SensorCreateDto create);

    /**
     * Sensor entity to read DTO.
     * @param sensor Sensor entity
     * @return Read DTO
     */
    @Mapping(target = "range.from", source = "rangeFrom")
    @Mapping(target = "range.to", source = "rangeTo")
    SensorReadDto entityToRead(Sensor sensor);
}
