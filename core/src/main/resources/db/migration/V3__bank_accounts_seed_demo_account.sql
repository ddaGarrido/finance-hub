INSERT INTO bank_accounts.bank_accounts (
    id, user_id, account_name, account_number, account_routing, account_type,
    institution_name, holder_name, balance, currency, active, created_at, updated_at
) VALUES (
    gen_random_uuid(),
    (SELECT id FROM user_mgmt.users WHERE username = 'admin'),
    'Demo Checking Account',
    '12345678901234',
    '987654321',
    'CHECKING',
    'Demo Bank',
    'Admin User',
    1000.00,
    'USD',
    TRUE,
    NOW(),
    NOW()
)
ON CONFLICT (account_number) DO NOTHING;