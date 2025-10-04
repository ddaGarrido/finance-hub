-- Description: Seed initial bill accounts for the admin user
INSERT INTO bills.bill_accounts (
  id, user_id, bill_account_name, bill_institution_name, bill_username, bill_password,
  active, created_at, updated_at
)
VALUES
  (gen_random_uuid(), (SELECT id FROM user_mgmt.users WHERE username='admin'), 'Conta Enel',    'Enel',     'user_enel', 'pass_enel', TRUE, NOW(), NOW()),
  (gen_random_uuid(), (SELECT id FROM user_mgmt.users WHERE username='admin'), 'Conta SABESP',  'SABESP',   'user_sabesp', 'pass_sabesp', TRUE, NOW(), NOW()),
  (gen_random_uuid(), (SELECT id FROM user_mgmt.users WHERE username='admin'), 'Conta Vivo',    'Vivo',     'user_vivo', 'pass_vivo', TRUE, NOW(), NOW()),
  (gen_random_uuid(), (SELECT id FROM user_mgmt.users WHERE username='admin'), 'Conta Claro',   'Claro',    'user_claro', 'pass_claro', TRUE, NOW(), NOW()),
  (gen_random_uuid(), (SELECT id FROM user_mgmt.users WHERE username='admin'), 'Conta Netflix', 'Netflix',  'user_netflix', 'pass_netflix', TRUE, NOW(), NOW())
ON CONFLICT DO NOTHING;