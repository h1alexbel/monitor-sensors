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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * User role.
 * @since 0.0.0
 */
@Entity
@Table(name = "roles", schema = "monitor_sensors")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRole {

    /**
     * ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name.
     */
    @Column(nullable = false, length = 16)
    private String name;

    /**
     * User of this role.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
