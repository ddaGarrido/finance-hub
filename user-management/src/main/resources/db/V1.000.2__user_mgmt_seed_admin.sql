-- Description: Seed initial admin user for user_mgmt schema
INSERT INTO user_mgmt.users (
    username, password, email, name, role, created_at, updated_at
) VALUES
    ('admin', '$2a$10$THGTqHPJoFbIqcOaeJA.6uMrwrpYL9rNOPznL19qt.cuiYTuageoq', 'admin@financehub.local', 'Administrator', 'ADMIN', NOW(),  NOW()),
    ('demoUser1', '', 'demo1@user.local', 'Demo User 1', 'USER', NOW(),  NOW()),
    ('demoUser2', '', 'demo2@user.local', 'Demo User 2', 'USER', NOW(),  NOW()),
    ('demoUser3', '', 'demo3@user.local', 'Demo User 3', 'USER', NOW(),  NOW()),
    ('demoUser4', '', 'demo4@user.local', 'Demo User 4', 'USER', NOW(),  NOW()),
    ('demoUser5', '', 'demo5@user.local', 'Demo User 5', 'USER', NOW(),  NOW())
ON CONFLICT (username) DO NOTHING;