DROP SEQUENCE IF EXISTS bills.bills_id_seq CASCADE;
CREATE SEQUENCE bills.bills_id_seq START WITH 1 INCREMENT BY 1 NO CYCLE;

-- Initialize the schema for bills
CREATE TABLE IF NOT EXISTS bills.bills (
  id BIGINT PRIMARY KEY DEFAULT nextval('bills.bills_id_seq'),
  bill_institution_id BIGINT NOT NULL REFERENCES bills.bill_institutions(id) ON DELETE CASCADE,
  user_id BIGINT NOT NULL REFERENCES user_mgmt.users(id) ON DELETE CASCADE,
  bill_account_id BIGINT REFERENCES bills.bill_accounts(id) ON DELETE CASCADE,
  type VARCHAR(20) NOT NULL,       -- BOLETO/UTILITY/CREDIT_CARD/SUBSCRIPTION/OTHER
  status VARCHAR(20) NOT NULL DEFAULT 'OPEN', -- OPEN/PAID/OVERDUE/CANCELED
  description VARCHAR(200) NOT NULL,
  due_date DATE NOT NULL,
  paid_at TIMESTAMPTZ,
  amount DECIMAL(19,2) NOT NULL,
  currency VARCHAR(3) NOT NULL DEFAULT 'BRL',
  tags_json JSONB,
  metadata_json JSONB,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

  CONSTRAINT fk_bills_user FOREIGN KEY (user_id) REFERENCES user_mgmt.users(id) ON DELETE CASCADE,
  CONSTRAINT fk_bills_account FOREIGN KEY (bill_account_id) REFERENCES bills.bill_accounts(id) ON DELETE SET NULL,
  CONSTRAINT fk_bills_institution FOREIGN KEY (bill_institution_id) REFERENCES bills.bill_institutions(id) ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS ix_bills_user ON bills.bills(user_id);
CREATE INDEX IF NOT EXISTS ix_bills_status_due ON bills.bills(status, due_date);
CREATE INDEX IF NOT EXISTS ix_bills_institution ON bills.bills(bill_institution_id);