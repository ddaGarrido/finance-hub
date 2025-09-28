-- Description: Seed initial admin user for user_mgmt schema
INSERT INTO user_mgmt.users (
    id, username, password, email, name, role, created_at, updated_at
) VALUES (
    gen_random_uuid(),
    'admin',
    '$2a$10$THGTqHPJoFbIqcOaeJA.6uMrwrpYL9rNOPznL19qt.cuiYTuageoq',
    'admin@financehub.local',
    'Administrator',
    'ADMIN',
    NOW(),
    NOW()
)
ON CONFLICT (username) DO NOTHING;