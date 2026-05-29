INSERT INTO vehicle_types (type_id, code, description, active)
VALUES (gen_random_uuid(), 'DRONE', 'Autonomous aerial surveillance quadcopter', true)
ON CONFLICT (code) DO NOTHING;

INSERT INTO vehicle_types (type_id, code, description, active)
VALUES (gen_random_uuid(), 'TANK', 'Heavy tracked armored ground vehicle', true)
ON CONFLICT (code) DO NOTHING;

INSERT INTO vehicle_types (type_id, code, description, active)
VALUES (gen_random_uuid(), 'SHIP', 'Naval surface tracking vessel platform', true)
ON CONFLICT (code) DO NOTHING;