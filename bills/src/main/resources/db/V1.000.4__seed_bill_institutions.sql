-- Description: Seed initial data into the bill_institutions table
-- This includes common utility and subscription providers in Brazil
INSERT INTO bills.bill_institutions (
    institution_key, institution_name, category, website_url, login_url, supports_webhook, active, metadata_json, created_at, updated_at
)
VALUES
  ('enel',  'Enel',     'ELECTRICITY', 'https://www.enel.com.br',     'https://www.enel.com.br', false, true, '{}', NOW(), NOW()),
  ('sabesp',   'SABESP',             'WATER',       'https://www.sabesp.com.br',   'https://agenciavirtual.sabesp.com.br', false, true, '{}', NOW(), NOW()),
  ('vivo',     'Vivo',               'TELECOM',     'https://www.vivo.com.br',     'https://login.vivo.com.br', false, true, '{}', NOW(), NOW()),
  ('claro',    'Claro',              'INTERNET',    'https://www.claro.com.br',    'https://minhaclaro.claro.com.br', false, true, '{}', NOW(), NOW()),
  ('netflix',  'Netflix',            'SUBSCRIPTION','https://www.netflix.com',     'https://www.netflix.com/login', false, true, '{}', NOW(), NOW())
ON CONFLICT (institution_key) DO NOTHING;