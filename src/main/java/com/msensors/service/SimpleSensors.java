/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.service;

import com.msensors.entity.Sensor;
import com.msensors.mapping.SensorMapper;
import com.msensors.repo.SensorRepo;
import com.msensors.rest.request.SensorCreateDto;
import com.msensors.rest.request.SensorRange;
import com.msensors.rest.request.SensorReadDto;
import com.msensors.rest.request.SensorUpdateDto;
import com.msensors.service.exception.SensorNotFoundException;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Simple service.
 *
 * @since 0.0.0
 * @checkstyle DesignForExtensionCheck (100 lines)
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SimpleSensors implements Sensors {

    /**
     * Repo.
     */
    private final SensorRepo repo;

    /**
     * Mapping.
     */
    private final SensorMapper mapping;

    @Transactional
    @Override
    public SensorReadDto save(final SensorCreateDto create) {
        final Sensor sensor = this.repo.save(this.mapping.createToEntity(create));
        SimpleSensors.log.info("Sensor with ID {} was saved", sensor.getId());
        return this.mapping.entityToRead(sensor);
    }

    @Override
    public SensorReadDto sensor(final Long identifier) {
        return this.repo.findById(identifier).map(this.mapping::entityToRead)
            .orElseThrow(
                () -> new SensorNotFoundException(
                    String.format(
                        "Sensor with ID %d not found",
                        identifier
                    )
                )
            );
    }

    @Override
    public Collection<SensorReadDto> all() {
        return this.repo.findAll().stream()
            .map(this.mapping::entityToRead)
            .toList();
    }

    @Transactional
    @Override
    public void delete(final Long identifier) {
        this.repo.findById(identifier)
            .ifPresentOrElse(
                sensor -> this.repo.deleteById(sensor.getId()),
                () -> {
                    throw new SensorNotFoundException(
                        String.format("Sensor with ID %d was not found", identifier)
                    );
                }
            );
    }

    @Transactional
    @Override
    public SensorReadDto update(final Long identifier, final SensorUpdateDto update) {
        final Sensor found = this.repo.findById(identifier)
            .orElseThrow(
                () -> new SensorNotFoundException(
                    String.format(
                        "Sensor with ID %d not found", identifier
                    )
                )
            );
        found.setName(update.getName());
        found.setModel(update.getModel());
        found.setType(update.getType());
        found.setUnit(update.getUnit());
        found.setLocation(update.getLocation());
        found.setDescription(update.getDescription());
        if (update.getRange() != null) {
            final SensorRange range = update.getRange();
            found.setRangeFrom(range.getFrom());
            found.setRangeTo(range.getTo());
        }
        return this.mapping.entityToRead(this.repo.save(found));
    }

    @Override
    public Collection<SensorReadDto> search(final String input) {
        return this.repo.findByNameContainingIgnoreCaseOrModelContainingIgnoreCase(input, input)
            .stream()
            .map(this.mapping::entityToRead)
            .toList();
    }
}
