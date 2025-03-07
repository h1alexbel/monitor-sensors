-- SPDX-FileCopyrightText: Copyright (c) 2025 Aliaksei Bialiauski
-- SPDX-License-Identifier: MIT
WITH inserted AS (
    INSERT INTO monitor_sensors.users (username, password)
    VALUES ('admin', '$2a$12$hXZgifn89EDT/Qf1JYHsg.XlU5SEN7BUA1YvgN./hzyBqI03Q4HNm')
    RETURNING id
)
INSERT INTO monitor_sensors.roles (name, user_id)
SELECT 'ADMIN', id FROM inserted
UNION ALL
SELECT 'VIEWER', id FROM inserted;
