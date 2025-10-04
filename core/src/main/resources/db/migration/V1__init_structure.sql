
-- Create user management schema and users table
DROP SCHEMA IF EXISTS user_mgmt CASCADE;
CREATE SCHEMA user_mgmt;

CREATE TABLE user_mgmt.users (
  id UUID PRIMARY KEY,
  username VARCHAR(60) NOT NULL UNIQUE,
  password VARCHAR(120) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  name VARCHAR(120) NOT NULL,
  role VARCHAR(40) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE UNIQUE INDEX IF NOT EXISTS ux_users_username ON user_mgmt.users(username);
CREATE UNIQUE INDEX IF NOT EXISTS ux_users_email ON user_mgmt.users(email);


-- Create Bank Account Management Schema and accounts table
DROP SCHEMA IF EXISTS bank_accounts CASCADE;
CREATE SCHEMA bank_accounts;

CREATE TABLE bank_accounts.bank_accounts (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  account_name VARCHAR(120) NOT NULL,
  account_number VARCHAR(14) NOT NULL UNIQUE,
  account_routing VARCHAR(9) NOT NULL,
  account_type VARCHAR(20) NOT NULL,
  institution_name VARCHAR(80) NOT NULL,
  holder_name VARCHAR(120) NOT NULL,
  balance DECIMAL(19,4) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT TRUE,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

--CREATE UNIQUE INDEX IF NOT EXISTS ux_bank_accounts_owner_key ON bank_accounts.bank_accounts(user_id, bank_code, branch, account_number);
--CREATE INDEX IF NOT EXISTS ix_bank_accounts_user ON bank_accounts.bank_accounts(user_id);


-- Create Bill Management Schema and bills table
DROP SCHEMA IF EXISTS bills CASCADE;
CREATE SCHEMA bills;

CREATE TABLE bills.bill_accounts (
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
