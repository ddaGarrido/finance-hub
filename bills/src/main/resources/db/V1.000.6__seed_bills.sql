-- Description: Seed initial data into the bill_institutions table
-- Tag: V1.000.6__seed_bills

-- 1) Retrieve user and bill institution references
WITH admin_user AS (
  SELECT id FROM user_mgmt.users WHERE username='admin' and id=1
),
bill_institutions AS (
  SELECT id, institution_key FROM bills.bill_institutions
  WHERE institution_key IN ('enel', 'sabesp', 'vivo', 'claro', 'netflix')
),
bill_accounts AS (
  SELECT id, name FROM bills.bill_accounts
  WHERE user_id = (SELECT id FROM admin_user)
)
INSERT INTO bills.bills (
  bill_institution_id, user_id, bill_account_id, type, status, description, due_date, amount, currency, tags_json, metadata_json
)
VALUES
  -- Electricity Bill - Enel
  ((SELECT id FROM bill_institutions WHERE institution_key = 'enel'), (SELECT id FROM admin_user),
   (SELECT id FROM bill_accounts WHERE name = 'Conta Enel'), 'BOLETO', 'OPEN', 'Conta de Luz - Enel', CURRENT_DATE + INTERVAL '5 days', 150.75, 'BRL',
   '["utilities", "electricity"]', '{}'),
  -- Water Bill - SABESP
  ((SELECT id FROM bill_institutions WHERE institution_key = 'sabesp'), (SELECT id FROM admin_user),
   (SELECT id FROM bill_accounts WHERE name = 'Conta Sabesp'), 'BOLETO', 'OPEN', 'Conta de Água - SABESP', CURRENT_DATE + INTERVAL '10 days', 80.50, 'BRL',
   '["utilities", "water"]', '{}'),
  -- Internet Bill - Claro
    ((SELECT id FROM bill_institutions WHERE institution_key = 'claro'), (SELECT id FROM admin_user),
   (SELECT id FROM bill_accounts WHERE name = 'Conta Claro'), 'BOLETO', 'OPEN', 'Conta de Internet - Claro', CURRENT_DATE + INTERVAL '3 days', 120.00, 'BRL',
   '["utilities", "internet"]', '{}'),
  -- Mobile Bill - Vivo
  ((SELECT id FROM bill_institutions WHERE institution_key = 'vivo'), (SELECT id FROM admin_user),
   (SELECT id FROM bill_accounts WHERE name = 'Conta Vivo'), 'BOLETO', 'OPEN', 'Conta de Celular - Vivo', CURRENT_DATE + INTERVAL '7 days', 90.30, 'BRL',
   '["utilities", "mobile"]', '{}'),
  -- Subscription Bill - Netflix
  ((SELECT id FROM bill_institutions WHERE institution_key = 'netflix'), (SELECT id FROM admin_user),
   (SELECT id FROM bill_accounts WHERE name = 'Conta Netflix'), 'SUBSCRIPTION', 'OPEN', 'Assinatura Netflix', CURRENT_DATE + INTERVAL '15 days', 45.90, 'BRL',
   '["subscription", "entertainment"]', '{}')
ON CONFLICT DO NOTHING;