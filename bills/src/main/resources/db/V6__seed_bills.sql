-- PLACE AT: bills/src/main/resources/db/migration/V3__bills_seed_5.sql
-- Fixed
-- * Uses admin user id
-- * Tries to resolve institution_id if bills.bill_institutions exists; otherwise sets NULL
-- * Safely inserts 5 sample bills

DO $$
DECLARE inst_exists boolean;
BEGIN
  SELECT EXISTS (
    SELECT 1 FROM information_schema.tables
    WHERE table_schema='bills' AND table_name='bill_institutions'
  ) INTO inst_exists;

  IF inst_exists THEN
    WITH admin_user AS (
      SELECT id AS user_id FROM user_mgmt.users WHERE username='admin' LIMIT 1
    ), inst AS (
      SELECT provider_key, id FROM bills.bill_institutions
    )
    INSERT INTO bills.bills (
      id, user_id, account_id, institution_id, type, status, description, due_date, paid_at, barcode,
      beneficiary_name, beneficiary_tax_id, payer_tax_id, amount, currency, tags_json, metadata_json,
      created_at, updated_at
    ) VALUES
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, (SELECT id FROM inst WHERE provider_key='enel-sp'),
        'UTILITY','OPEN','Conta de luz Enel (Set)', CURRENT_DATE + 5, NULL, NULL,
        'Enel', NULL, NULL, 189.90, 'BRL','["energia","casa"]'::jsonb,'{"ref":"2025-09"}'::jsonb, NOW(), NOW()),
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, (SELECT id FROM inst WHERE provider_key='sabesp'),
        'UTILITY','OVERDUE','Conta de água SABESP (Ago)', CURRENT_DATE - 10, NULL, NULL,
        'SABESP', NULL, NULL, 96.50, 'BRL','["agua","casa"]'::jsonb,'{"ref":"2025-08"}'::jsonb, NOW(), NOW()),
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, (SELECT id FROM inst WHERE provider_key='netflix'),
        'SUBSCRIPTION','PAID','Assinatura Netflix', CURRENT_DATE - 2, NOW() - INTERVAL '1 day', NULL,
        'Netflix', NULL, NULL, 55.90, 'BRL','["entretenimento"]'::jsonb,'{"cycle":"mensal"}'::jsonb, NOW(), NOW()),
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, (SELECT id FROM inst WHERE provider_key='vivo'),
        'UTILITY','OPEN','Conta Vivo (Set)', CURRENT_DATE + 12, NULL, NULL,
        'Vivo', NULL, NULL, 79.99, 'BRL','["telefone","celular"]'::jsonb,'{"ref":"2025-09"}'::jsonb, NOW(), NOW()),
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, (SELECT id FROM inst WHERE provider_key='claro'),
        'UTILITY','CANCELED','Internet Claro', CURRENT_DATE + 1, NULL, NULL,
        'Claro', NULL, NULL, 119.99, 'BRL','["internet","casa"]'::jsonb,'{"motivo":"migração de plano"}'::jsonb, NOW(), NOW())
    ON CONFLICT DO NOTHING;
  ELSE
    WITH admin_user AS (
      SELECT id AS user_id FROM user_mgmt.users WHERE username='admin' LIMIT 1
    )
    INSERT INTO bills.bills (
      id, user_id, account_id, institution_id, type, status, description, due_date, paid_at, barcode,
      beneficiary_name, beneficiary_tax_id, payer_tax_id, amount, currency, tags_json, metadata_json,
      created_at, updated_at
    ) VALUES
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, NULL,
        'UTILITY','OPEN','Conta de luz (Set)', CURRENT_DATE + 5, NULL, NULL,
        'Concessionária', NULL, NULL, 189.90, 'BRL','["energia","casa"]'::jsonb,'{"ref":"2025-09"}'::jsonb, NOW(), NOW()),
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, NULL,
        'UTILITY','OVERDUE','Conta de água (Ago)', CURRENT_DATE - 10, NULL, NULL,
        'Companhia de Água', NULL, NULL, 96.50, 'BRL','["agua","casa"]'::jsonb,'{"ref":"2025-08"}'::jsonb, NOW(), NOW()),
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, NULL,
        'SUBSCRIPTION','PAID','Assinatura Streaming', CURRENT_DATE - 2, NOW() - INTERVAL '1 day', NULL,
        'Streaming', NULL, NULL, 55.90, 'BRL','["entretenimento"]'::jsonb,'{"cycle":"mensal"}'::jsonb, NOW(), NOW()),
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, NULL,
        'UTILITY','OPEN','Conta Telefonia (Set)', CURRENT_DATE + 12, NULL, NULL,
        'Operadora', NULL, NULL, 79.99, 'BRL','["telefone","celular"]'::jsonb,'{"ref":"2025-09"}'::jsonb, NOW(), NOW()),
      (gen_random_uuid(), (SELECT user_id FROM admin_user), NULL, NULL,
        'UTILITY','CANCELED','Internet Banda Larga', CURRENT_DATE + 1, NULL, NULL,
        'Provedor', NULL, NULL, 119.99, 'BRL','["internet","casa"]'::jsonb,'{"motivo":"migração de plano"}'::jsonb, NOW(), NOW())
    ON CONFLICT DO NOTHING;
  END IF;
END $$;
