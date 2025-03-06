-- SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
-- SPDX-License-Identifier: MIT
CREATE SCHEMA monitor_sensors;

CREATE TABLE monitor_sensors.sensors(
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(32) NOT NULL,
  model VARCHAR(16) NOT NULL,
  range_from INT,
  range_to INT NOT NULL,
  type VARCHAR (32) NOT NULL,
  unit VARCHAR(16),
  location VARCHAR(42),
  description VARCHAR(216)
);

CREATE TABLE monitor_sensors.users(
  id BIGSERIAL PRIMARY KEY,
  username VARCHAR(64) UNIQUE NOT NULL,
  password VARCHAR(256) NOT NULL
);

CREATE TABLE monitor_sensors.roles(
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(16) NOT NULL,
  user_id BIGINT REFERENCES monitor_sensors.users(id)
);
