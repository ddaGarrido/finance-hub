-- Description: Initialize the bill_accounts table structure
DROP SEQUENCE IF EXISTS bills.bill_accounts_id_seq CASCADE;
CREATE SEQUENCE bills.bill_accounts_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

CREATE TABLE IF NOT EXISTS bills.bill_accounts (
  id BIGINT PRIMARY KEY DEFAULT nextval('bills.bill_accounts_id_seq'),
  bill_institution_id BIGINT NOT NULL REFERENCES bills.bill_institutions(id) ON DELETE CASCADE,
  user_id BIGINT NOT NULL REFERENCES user_mgmt.users(id) ON DELETE CASCADE,
  name VARCHAR(120) NOT NULL,
  username VARCHAR(120) NOT NULL,
  password VARCHAR(120) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS ix_bill_accounts_user ON bills.bill_accounts(user_id);
CREATE INDEX IF NOT EXISTS ix_bill_accounts_institution ON bills.bill_accounts(bill_institution_id);
CREATE INDEX IF NOT EXISTS ix_bill_accounts_active ON bills.bill_accounts(active);