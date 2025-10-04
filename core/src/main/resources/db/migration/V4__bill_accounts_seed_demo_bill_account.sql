INSERT INTO bills.bill_accounts (
    id, user_id, bill_account_name, bill_institution_name, bill_username, bill_password,
    active, created_at, updated_at
) VALUES (
    gen_random_uuid(),
    (SELECT id FROM user_mgmt.users WHERE username = 'admin'),
    'Demo Bill Account',
    'Leste Telecom',
    'username',
    'password',
    TRUE,
    NOW(),
    NOW()
)
ON CONFLICT DO NOTHING;