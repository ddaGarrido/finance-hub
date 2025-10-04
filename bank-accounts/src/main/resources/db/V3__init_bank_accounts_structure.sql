-- Description: Initial structure for bank accounts module
CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS bank_accounts.bank_accounts (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

  user_id UUID NOT NULL,
  account_name VARCHAR(120) NOT NULL,
  account_number VARCHAR(14) NOT NULL,
  account_routing VARCHAR(9) NOT NULL,
  account_type VARCHAR(20) NOT NULL,
  institution_name VARCHAR(80) NOT NULL,
  holder_name VARCHAR(120) NOT NULL,
  balance NUMERIC(19,4) NOT NULL DEFAULT 0,
  currency VARCHAR(3) NOT NULL DEFAULT 'BRL',
  active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE UNIQUE INDEX IF NOT EXISTS ux_bank_accounts_user_number
  ON bank_accounts.bank_accounts(user_id, account_number);

CREATE INDEX IF NOT EXISTS ix_bank_accounts_user
  ON bank_accounts.bank_accounts(user_id);