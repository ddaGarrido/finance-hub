-- Description : Initial structure for bank account institutions
CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP SCHEMA IF EXISTS bank_accounts CASCADE;
DROP SEQUENCE IF EXISTS bank_accounts.bank_account_institutions_id_seq CASCADE;

CREATE SCHEMA bank_accounts;
CREATE SEQUENCE bank_accounts.bank_account_institutions_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

CREATE TABLE IF NOT EXISTS bank_accounts.bank_account_institutions (
  id BIGINT PRIMARY KEY DEFAULT nextval('bank_accounts.bank_account_institutions_id_seq'),
  code VARCHAR(4),
  name VARCHAR(120) NOT NULL,
  short_name VARCHAR(60),
  website_url VARCHAR(255),
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS ix_bank_inst_active ON bank_accounts.bank_account_institutions(active);