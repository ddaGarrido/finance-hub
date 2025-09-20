CREATE SCHEMA IF NOT EXISTS user_mgmt;
CREATE TABLE IF NOT EXISTS user_mgmt.users (
  id UUID PRIMARY KEY,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ NOT NULL,
  username VARCHAR(60) NOT NULL UNIQUE,
  password VARCHAR(120) NOT NULL,
  email VARCHAR(120) NOT NULL UNIQUE,
  name VARCHAR(120) NOT NULL,
  role VARCHAR(40) NOT NULL
);

CREATE SCHEMA IF NOT EXISTS bank_accounts_mgmt;
CREATE TABLE IF NOT EXISTS bank_accounts_mgmt.bank_accounts (
  id UUID PRIMARY KEY,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ NOT NULL,
  owner_id UUID NOT NULL,
  account_name VARCHAR(120) NOT NULL,
  account_number VARCHAR(30) NOT NULL UNIQUE,
  account_routing VARCHAR(9) NOT NULL,
  bank_name VARCHAR(120) NOT NULL,
  balance DECIMAL(19,4) NOT NULL,
  currency VARCHAR(3) NOT NULL
);