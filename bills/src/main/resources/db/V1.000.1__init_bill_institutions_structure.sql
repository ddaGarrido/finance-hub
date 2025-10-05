CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Initialize the schema for bill institutions
DROP SCHEMA IF EXISTS bills CASCADE;
DROP SEQUENCE IF EXISTS bills.bill_institutions_id_seq CASCADE;

CREATE SCHEMA bills;
CREATE SEQUENCE bills.bill_institutions_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

CREATE TABLE IF NOT EXISTS bills.bill_institutions (
  id BIGINT PRIMARY KEY DEFAULT nextval('bills.bill_institutions_id_seq'),
  institution_key VARCHAR(80) NOT NULL UNIQUE, -- stable key, e.g. 'enel-sp'
  institution_name VARCHAR(120) NOT NULL,       -- human friendly
  category VARCHAR(32) NOT NULL,            -- ELECTRICITY/WATER/INTERNET/TELECOM/SUBSCRIPTION/OTHER
  website_url VARCHAR(255),
  login_url   VARCHAR(255),
  supports_webhook BOOLEAN NOT NULL DEFAULT FALSE,
  active BOOLEAN NOT NULL DEFAULT FALSE,
  metadata_json JSONB,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS ix_bill_institutions_active ON bills.bill_institutions(active);