INSERT INTO users (id, version, name, email) VALUES
  ('6d2c6f7a-8f2d-4c6a-9d79-2a2f0c5f7a01', 0, 'Alice Student', 'alice.student@example.com'),
  ('e3a1b2c3-4d5e-4f60-9a7b-1c2d3e4f5061', 0, 'Bob Student', 'bob.student@example.com'),
  ('b7c8d9e0-1f23-4a45-8c9d-0e1f2a3b4c5d', 0, 'Carol Trainer', 'carol.trainer@example.com');

INSERT INTO user_roles (user_id, role) VALUES
  ('6d2c6f7a-8f2d-4c6a-9d79-2a2f0c5f7a01', 'STUDENT'),
  ('e3a1b2c3-4d5e-4f60-9a7b-1c2d3e4f5061', 'STUDENT'),
  ('b7c8d9e0-1f23-4a45-8c9d-0e1f2a3b4c5d', 'TRAINER');

INSERT INTO trainers (id, version, user_id, profile_description) VALUES
  ('2c1a5b7d-8e9f-4a12-b3c4-5d6e7f8091a2', 0, 'b7c8d9e0-1f23-4a45-8c9d-0e1f2a3b4c5d', 'Experienced Java and Spring Boot trainer');

INSERT INTO enrollments (id, version, user_id, status, created_at) VALUES
  ('a1b2c3d4-e5f6-47a8-9b0c-1d2e3f4a5b6c', 0, '6d2c6f7a-8f2d-4c6a-9d79-2a2f0c5f7a01', 'CONFIRMED', '2026-02-10T09:15:00Z'),
  ('c7d8e9f0-1a2b-4c5d-8e9f-0a1b2c3d4e5f', 0, 'e3a1b2c3-4d5e-4f60-9a7b-1c2d3e4f5061', 'CANCELLED', '2026-02-10T10:30:00Z');
