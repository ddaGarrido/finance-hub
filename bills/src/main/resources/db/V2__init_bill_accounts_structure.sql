-- Description: Initialize the bill_accounts table structure
CREATE TABLE IF NOT EXISTS bills.bill_accounts (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  bill_account_name VARCHAR(120) NOT NULL,
  bill_institution_name VARCHAR(120) NOT NULL,
  bill_username VARCHAR(120) NOT NULL,
  bill_password VARCHAR(120) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS ix_bill_accounts_user
  ON bills.bill_accounts(user_id);