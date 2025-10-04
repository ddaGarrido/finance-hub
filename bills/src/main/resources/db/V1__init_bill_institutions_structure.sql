CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Initialize the schema for bill institutions
DROP SCHEMA IF EXISTS bills CASCADE;
CREATE SCHEMA bills;

CREATE TABLE IF NOT EXISTS bills.bill_institutions (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  provider_key VARCHAR(80) NOT NULL UNIQUE, -- stable key, e.g. 'enel-sp'
  display_name VARCHAR(120) NOT NULL,       -- human friendly
  category VARCHAR(32) NOT NULL,            -- ELECTRICITY/WATER/INTERNET/TELECOM/SUBSCRIPTION/OTHER
  website_url VARCHAR(255),
  login_url   VARCHAR(255),
  supports_webhook BOOLEAN NOT NULL DEFAULT FALSE,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  metadata_json JSONB,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS ix_bill_institutions_active
  ON bills.bill_institutions(active);