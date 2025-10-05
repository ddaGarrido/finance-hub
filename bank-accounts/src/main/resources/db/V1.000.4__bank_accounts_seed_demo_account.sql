-- Description: Seed demo bank accounts for the admin user
WITH admin_user AS (
  SELECT id AS user_id
  FROM user_mgmt.users
  WHERE username = 'admin' AND id = 1 LIMIT 1
),
bank_institutions AS (
  SELECT id AS id, code
  FROM bank_accounts.bank_account_institutions
  WHERE code IN ('001', '033', '341', '237', '260')
)
INSERT INTO bank_accounts.bank_accounts (
  bank_institution_id, user_id, name, number, routing, type,
  holder_name, balance, currency, active, created_at, updated_at
)
VALUES
  ((SELECT id FROM bank_institutions WHERE code = '001'), (SELECT user_id FROM admin_user), 'Conta Corrente Principal', '00012345678901', '001000001', 'CHECKING', 'Admin User',  2500.00, 'BRL', TRUE, NOW(), NOW()),
  ((SELECT id FROM bank_institutions WHERE code = '033'), (SELECT user_id FROM admin_user), 'Conta Poupança',          '00012345678902', '033000001', 'SAVINGS',   'Admin User',  350.75,  'BRL', TRUE, NOW(), NOW()),
  ((SELECT id FROM bank_institutions WHERE code = '341'), (SELECT user_id FROM admin_user), 'Conta Salário',           '00012345678903', '341000001', 'CHECKING',  'Admin User',  120.00,  'BRL', TRUE, NOW(), NOW()),
  ((SELECT id FROM bank_institutions WHERE code = '237'), (SELECT user_id FROM admin_user), 'Conta Investimentos',     '00012345678904', '237000001', 'BROKERAGE', 'Admin User',  0.00,    'BRL', TRUE, NOW(), NOW()),
  ((SELECT id FROM bank_institutions WHERE code = '260'), (SELECT user_id FROM admin_user), 'Conta Nubank',            '00012345678905', '260000001', 'CHECKING',  'Admin User',  890.10,  'BRL', TRUE, NOW(), NOW())
ON CONFLICT DO NOTHING;
