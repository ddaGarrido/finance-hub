CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Initialize the schema for bills
CREATE TABLE IF NOT EXISTS bills.bills (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  user_id UUID NOT NULL,
  account_id UUID,                 -- FK to bills.bill_accounts(id) if available
  institution_id UUID,             -- FK to bills.bill_institutions(id)
  type VARCHAR(20) NOT NULL,       -- BOLETO/UTILITY/CREDIT_CARD/SUBSCRIPTION/OTHER
  status VARCHAR(20) NOT NULL DEFAULT 'OPEN', -- OPEN/PAID/OVERDUE/CANCELED
  description VARCHAR(200) NOT NULL,
  due_date DATE NOT NULL,
  paid_at TIMESTAMPTZ,
  barcode VARCHAR(64),
  beneficiary_name VARCHAR(120),
  beneficiary_tax_id VARCHAR(20),
  payer_tax_id VARCHAR(20),
  amount DECIMAL(19,2) NOT NULL,
  currency VARCHAR(3) NOT NULL DEFAULT 'BRL',
  tags_json JSONB,
  metadata_json JSONB,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  CONSTRAINT fk_bills_user FOREIGN KEY (user_id) REFERENCES user_mgmt.users(id) ON DELETE CASCADE,
  CONSTRAINT fk_bills_account FOREIGN KEY (account_id) REFERENCES bills.bill_accounts(id) ON DELETE SET NULL,
  CONSTRAINT fk_bills_institution FOREIGN KEY (institution_id) REFERENCES bills.bill_institutions(id) ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS ix_bills_user ON bills.bills(user_id);
CREATE INDEX IF NOT EXISTS ix_bills_status_due ON bills.bills(status, due_date);
CREATE INDEX IF NOT EXISTS ix_bills_institution ON bills.bills(institution_id);