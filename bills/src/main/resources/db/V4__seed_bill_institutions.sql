-- Description: Seed initial data into the bill_institutions table
-- This includes common utility and subscription providers in Brazil
INSERT INTO bills.bill_institutions (provider_key, display_name, category, website_url, login_url, supports_webhook, active)
VALUES
  ('enel',  'Enel',     'ELECTRICITY', 'https://www.enel.com.br',     'https://www.enel.com.br', false, true),
  ('sabesp',   'SABESP',             'WATER',       'https://www.sabesp.com.br',   'https://agenciavirtual.sabesp.com.br', false, true),
  ('vivo',     'Vivo',               'TELECOM',     'https://www.vivo.com.br',     'https://login.vivo.com.br', false, true),
  ('claro',    'Claro',              'INTERNET',    'https://www.claro.com.br',    'https://minhaclaro.claro.com.br', false, true),
  ('netflix',  'Netflix',            'SUBSCRIPTION','https://www.netflix.com',     'https://www.netflix.com/login', false, true)
ON CONFLICT (provider_key) DO NOTHING;