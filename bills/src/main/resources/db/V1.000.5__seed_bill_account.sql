-- Description: Seed initial bill accounts for the admin user
WITH admin_user AS (
  SELECT id FROM user_mgmt.users WHERE username='admin' and id=1 
),
bill_institutions AS (
  SELECT id, institution_key FROM bills.bill_institutions
  WHERE institution_key IN ('enel', 'sabesp', 'vivo', 'claro', 'netflix')
)

INSERT INTO bills.bill_accounts (
  bill_institution_id, user_id, name, username, password
)
VALUES
  ((SELECT id FROM bill_institutions WHERE institution_key = 'enel'), (SELECT id FROM admin_user), 'Conta Enel', 'user_enel', 'pass_enel'),
  ((SELECT id FROM bill_institutions WHERE institution_key = 'sabesp'), (SELECT id FROM admin_user), 'Conta Sabesp', 'user_sabesp', 'pass_sabesp'),
  ((SELECT id FROM bill_institutions WHERE institution_key = 'vivo'), (SELECT id FROM admin_user), 'Conta Vivo', 'user_vivo', 'pass_vivo'),
  ((SELECT id FROM bill_institutions WHERE institution_key = 'claro'), (SELECT id FROM admin_user), 'Conta Claro', 'user_claro', 'pass_claro'),
  ((SELECT id FROM bill_institutions WHERE institution_key = 'netflix'), (SELECT id FROM admin_user), 'Conta Netflix', 'user_netflix', 'pass_netflix')
ON CONFLICT DO NOTHING;