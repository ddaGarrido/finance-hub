-- Description : Initial structure for bank account institutions
DROP SCHEMA IF EXISTS bank_accounts CASCADE;
CREATE SCHEMA bank_accounts;
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS bank_accounts.bank_account_institutions (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  compe VARCHAR(4),       -- 3-4 digit bank code (e.g., 341, 260)
  ispb  VARCHAR(8),       -- 8-digit ISPB (where known)
  name VARCHAR(120) NOT NULL,
  short_name VARCHAR(60),
  website_url VARCHAR(255),
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  CONSTRAINT uq_bank_inst_compe UNIQUE (compe),
  CONSTRAINT uq_bank_inst_ispb UNIQUE (ispb)
);

CREATE INDEX IF NOT EXISTS ix_bank_inst_active ON bank_accounts.bank_account_institutions(active);