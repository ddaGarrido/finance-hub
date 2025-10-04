-- Description: Seed demo bank accounts for the admin user
WITH admin_user AS (
  SELECT id AS user_id FROM user_mgmt.users WHERE username = 'admin' LIMIT 1
)
INSERT INTO bank_accounts.bank_accounts (
  id, user_id, account_name, account_number, account_routing, account_type,
  institution_name, holder_name, balance, currency, active, created_at, updated_at
)
VALUES
  (gen_random_uuid(), (SELECT user_id FROM admin_user), 'Conta Corrente Principal', '00012345678901', '001000001', 'CHECKING',    'Banco do Brasil', 'Admin User',  2500.00, 'BRL', TRUE, NOW(), NOW()),
  (gen_random_uuid(), (SELECT user_id FROM admin_user), 'Conta Poupança',          '00012345678902', '033000001', 'SAVINGS',     'Santander',       'Admin User',  350.75,  'BRL', TRUE, NOW(), NOW()),
  (gen_random_uuid(), (SELECT user_id FROM admin_user), 'Conta Salário',           '00012345678903', '341000001', 'CHECKING',    'Itaú',            'Admin User',  120.00,  'BRL', TRUE, NOW(), NOW()),
  (gen_random_uuid(), (SELECT user_id FROM admin_user), 'Conta Investimentos',     '00012345678904', '237000001', 'BROKERAGE',   'Bradesco',        'Admin User',  0.00,    'BRL', TRUE, NOW(), NOW()),
  (gen_random_uuid(), (SELECT user_id FROM admin_user), 'Conta Nubank',            '00012345678905', '260000001', 'CHECKING',    'Nubank',          'Admin User',  890.10,  'BRL', TRUE, NOW(), NOW())
ON CONFLICT (user_id, account_number) DO NOTHING;
