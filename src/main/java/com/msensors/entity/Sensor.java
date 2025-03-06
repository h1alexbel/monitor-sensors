/*
 * SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
 * SPDX-License-Identifier: MIT
 */
package com.msensors.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Sensor in the database.
 * @since 0.0.0
 */
@Entity
@Table(name = "sensors", schema = "monitor_sensors")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Sensor {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name.
     */
    @Column(nullable = false, length = 32)
    private String name;

    /**
     * Model.
     */
    @Column(nullable = false, length = 16)
    private String model;

    /**
     * Range from.
     * @checkstyle MemberNameCheck (3 lines)
     */
    private Integer rangeFrom;

    /**
     * Range to.
     * @checkstyle MemberNameCheck (3 lines)
     */
    @Column(nullable = false)
    private Integer rangeTo;

    /**
     * Type.
     */
    @Column(nullable = false, length = 32)
    private String type;

    /**
     * Unit.
     */
    @Column(length = 16)
    private String unit;

    /**
     * Location.
     */
    @Column(length = 42)
    private String location;

    /**
     * Description.
     */
    @Column(length = 216)
    private String description;
}
