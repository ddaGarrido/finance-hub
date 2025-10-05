-- Description: Initial structure for bank accounts module
DROP SEQUENCE IF EXISTS bank_accounts.bank_accounts_id_seq CASCADE;

CREATE SEQUENCE IF NOT EXISTS bank_accounts.bank_accounts_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

CREATE TABLE IF NOT EXISTS bank_accounts.bank_accounts (
  id BIGINT PRIMARY KEY DEFAULT nextval('bank_accounts.bank_accounts_id_seq'),
  bank_institution_id BIGINT NOT NULL REFERENCES bank_accounts.bank_account_institutions(id) ON DELETE CASCADE,
  user_id BIGINT NOT NULL REFERENCES user_mgmt.users(id) ON DELETE CASCADE,
  name VARCHAR(120) NOT NULL,
  number VARCHAR(14) NOT NULL,
  routing VARCHAR(9) NOT NULL,
  type VARCHAR(20) NOT NULL,
  holder_name VARCHAR(120) NOT NULL,
  balance NUMERIC(19,4) NOT NULL DEFAULT 0,
  currency VARCHAR(3) NOT NULL DEFAULT 'BRL',
  active BOOLEAN NOT NULL DEFAULT TRUE,

  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS ux_bank_accounts_user_number ON bank_accounts.bank_accounts(user_id, number);
CREATE INDEX IF NOT EXISTS ix_bank_accounts_user ON bank_accounts.bank_accounts(user_id);